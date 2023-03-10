package dojo.joyofkotlin.ch7

import java.io.IOException
import java.io.Serializable

sealed class Result<out A> : Serializable {
    internal class Failure<out A>(
        internal val exception: RuntimeException
    ) : Result<A>() {
        override fun toString(): String {
            return "Failure(${exception.message})"
        }
    }

    internal class Success<out A>(
        internal val value: A
    ) : Result<A>() {
        override fun toString(): String {
            return "Success($value)"
        }
    }

    companion object {
        operator fun <A> invoke(a: A? = null): Result<A> = when (a) {
            null -> Failure(NullPointerException())
            else -> Success(a)
        }

        fun <A> failure(message: String): Result<A> = Failure(IllegalStateException(message))

        fun <A> failure(exception: RuntimeException): Result<A> = Failure(exception)

        fun <A> failure(exception: Exception): Result<A> = Failure(IllegalStateException(exception))
    }

    /**
     * p.279 7-4
     */
//    fun <B> map(f: (A) -> B): Result<B> = flatMap { invoke(f(it)) }

    fun <B> map(f: (A) -> B): Result<B> = when (this) {
        is Failure -> failure(this.exception)
        is Success -> {
            try {
                val r = f(this.value)
                invoke(r)
            } catch (e: Exception) {
                failure(e)
            }
        }
    }

    fun <B> flatMap(f: (A) -> Result<B>): Result<B> = when (this) {
        is Failure -> failure(this.exception)
        is Success -> {
            try {
                f(this.value)
            } catch (e: Exception) {
                failure(e)
            }
        }
    }

    fun getOrElse(defaultValue: @UnsafeVariance A): A = when (this) {
        is Failure -> defaultValue
        is Success -> this.value
    }

    fun orElse(defaultValue: () -> Result<@UnsafeVariance A>): Result<A> = when (this) {
        is Success -> this
        is Failure -> {
            try {
                defaultValue()
            } catch (e: Exception) {
                failure(e)
            }
        }
    }
}

fun <K, V> Map<K, V>.getResult(key: K) = when {
    this.containsKey(key) -> Result(this[key])
    else -> Result.failure("Key $key not found in map")
}

data class Toon (
    val firstName: String,
    val lastName: String,
    val email: Result<String>,
) {
    companion object {
        operator fun invoke(firstName: String, lastName: String) =
            Toon(firstName, lastName, Result.failure("$firstName $lastName has no email"))

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
    else -> Result.failure("Invalid name $name")
}
