package newresult

import java.io.IOException
import java.io.Serializable

sealed class Result<out A> : Serializable {
    abstract fun <B> map(f: (A) -> B): Result<B>
    abstract fun <B> flatMap(f: (A) -> Result<B>): Result<B>

    fun getOrElse(defaultValue: @UnsafeVariance A): A = when (this) {
        is Success -> this.value
        else -> defaultValue
    }

    fun getOrElse(defaultFunction: () -> @UnsafeVariance A): A = when (this) {
        is Success -> this.value
        else -> defaultFunction()
    }

    fun orElse(defaultFunction: () -> Result<@UnsafeVariance A>): Result<A> = when (this) {
        is Success -> this
        else -> try {
            defaultFunction()
        } catch (e: RuntimeException) {
            Result.failure<A>(e)
        } catch (e: Exception) {
            Result.failure<A>(RuntimeException(e))
        }
    }

    /**
     * p.288 7-5
     */
    fun filter(p: (A) -> Boolean): Result<A> = flatMap { if (p(it)) this else Result.invoke() }
    fun filter(p: (A) -> Boolean, message: String): Result<A> = flatMap { if (p(it)) this else Result.failure(message) }

    /**
     * p.289 7-6
     */
//    fun exists(p: (A) -> Boolean): Boolean = filter(p) is Success
    fun exists(p: (A) -> Boolean): Boolean = map(p).getOrElse(false)

    /**
     * p.290 7-7
     */
    abstract fun mapFailure(message: String): Result<A>

    /**
     * p.293 7-9
     */
    fun forEach(effect: (A) -> Unit) {
        when (this) {
            is Success -> effect(this.value)
            else -> {}
        }
    }

    /**
     * p.293 7-10
     */
    fun forEachOrElse(onSuccess: (A) -> Unit, onFailure: (RuntimeException) -> Unit, onEmpty: () -> Unit) {
        when (this) {
            is Success -> onSuccess(this.value)
            is Failure -> onFailure(this.exception)
            is Empty -> onEmpty()
        }
    }

    /**
     * p.294 7-11
     */
    fun forEach(onSuccess: (A) -> Unit = {}, onFailure: (RuntimeException) -> Unit = {}, onEmpty: () -> Unit = {}) {
        forEachOrElse(onSuccess, onFailure, onEmpty)
    }

    internal object Empty : Result<Nothing>() {
        override fun <B> map(f: (Nothing) -> B): Result<B> = Empty
        override fun <B> flatMap(f: (Nothing) -> Result<B>): Result<B> = Empty
        override fun mapFailure(message: String): Result<Nothing> = this

        override fun toString(): String = "Empty"
    }

    internal class Failure<out A>(val exception: RuntimeException) : Result<A>() {
        override fun <B> map(f: (A) -> B): Result<B> = Failure(exception)

        override fun <B> flatMap(f: (A) -> Result<B>): Result<B> = Failure(exception)
        override fun mapFailure(message: String): Result<A> = failure(RuntimeException(message, this.exception))
        override fun toString(): String = "Failure(${exception.message})"
    }

    internal class Success<out A>(val value: A) : Result<A>() {
        override fun <B> map(f: (A) -> B): Result<B> = try {
            Success(f(value))
        } catch (e: RuntimeException) {
            Failure(e)
        } catch (e: Exception) {
            Failure(RuntimeException(e))
        }

        override fun <B> flatMap(f: (A) -> Result<B>): Result<B> = try {
            f(value)
        } catch (e: RuntimeException) {
            Failure(e)
        } catch (e: Exception) {
            Failure(RuntimeException(e))
        }

        override fun mapFailure(message: String): Result<A> = this

        override fun toString(): String = "Success($value)"
    }

    companion object {
        /**
         * p.291 7-8
         */
        operator fun <A> invoke(a: A? = null, message: String): Result<A> = when (a) {
            null -> Failure(NullPointerException(message))
            else -> Success(a)
        }

        operator fun <A> invoke(a: A? = null, p: (A) -> Boolean): Result<A> = when (a) {
            null -> Failure(NullPointerException())
            else -> if (p(a)) Success(a) else Empty
        }

        operator fun <A> invoke(a: A? = null, message: String, p: (A) -> Boolean): Result<A> = when (a) {
            null -> Failure(NullPointerException())
            else -> if (p(a)) Success(a) else failure(IllegalArgumentException(message))
        }

        operator fun <A> invoke(a: A? = null): Result<A> = when (a) {
            null -> Failure(NullPointerException())
            else -> Success(a)
        }

        operator fun <A> invoke(): Result<A> = Empty

        fun <A> failure(message: String): Result<A> = Failure(IllegalStateException(message))

        fun <A> failure(exception: RuntimeException): Result<A> = Failure(exception)

        fun <A> failure(exception: Exception): Result<A> = Failure(IllegalStateException(exception))
    }

}

fun <K, V> Map<K, V>.getResult(key: K) = when {
    this.containsKey(key) -> Result(this[key])
    else -> Result.Empty
}

/**
 * p.296 7-12
 */
fun <A, B> lift(f: (A) -> B): (Result<A>) -> Result<B> = { it.map(f) }

/**
 * p.296 7-13
 */
fun <A, B, C> lift2(f: (A) -> (B) -> C): (Result<A>) -> (Result<B>) -> Result<C> = { ra ->
    { rb ->
//        rb.flatMap { b -> ra.map { a -> f(a)(b) } }
//        ra.flatMap { a -> rb.map { b -> f(a)(b) } }
        ra.map(f).flatMap(rb::map)
    }
}

fun <A, B, C, D> lift3(f: (A) -> (B) -> (C) -> D): (Result<A>) -> (Result<B>) -> (Result<C>) -> Result<D> =
    { ra -> { rb -> { rc -> ra.map(f).flatMap(rb::map).flatMap(rc::map) } } }

/**
 * p.297 7-14
 */
fun <A, B, C> map2(ra: Result<A>, rb: Result<B>, f: (A) -> (B) -> C): Result<C> = lift2(f)(ra)(rb)

data class Toon(
    val firstName: String,
    val lastName: String,
    val email: Result<String>,
) {
    companion object {
        operator fun invoke(firstName: String, lastName: String) =
            Toon(firstName, lastName, Result.Empty)

        operator fun invoke(firstName: String, lastName: String, email: String) =
            Toon(firstName, lastName, Result(email))
    }
}

fun main() {
    val toons = mapOf(
        "Mickey" to Toon("Mickey", "Mouse", "mickey@disney.com"),
        "Minnie" to Toon("Minnie", "Mouse"),
        "Donald" to Toon("Donald", "Duck", "donald@disney.com"),
    )

    val toon = getName()
        .flatMap(toons::getResult)
        .flatMap(Toon::email)

    println(toon)
}

fun getName(): Result<String> = try {
    validate(readLine())
} catch (e: IOException) {
    Result.failure(e)
}

fun validate(name: String?): Result<String> = when {
    name?.isNotEmpty() ?: false -> Result(name)
    else -> Result.failure(IOException())
}
