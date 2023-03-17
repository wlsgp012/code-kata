package dojo.joyofkotlin.ch7

sealed class Either<out E, out A> {
    companion object {
        fun <E, A> left(value: E): Either<E, A> = Left(value)
        fun <E, A> right(value: A): Either<E, A> = Right(value)
    }

    /**
     * p.275 7-1
     */
    abstract fun <B> map(f: (A) -> B): Either<E, B>

    /**
     * p.276 7-2
     */
    abstract fun <B> flatMap(f: (A) -> Either<@UnsafeVariance E, B>): Either<E, B>

    /**
     * p.277 7-3
     */
    fun getOrElse(defaultValue: () -> @UnsafeVariance A): A = when (this) {
        is Left -> defaultValue()
        is Right -> this.value
    }

    fun orElse(defaultValue: () -> Either<@UnsafeVariance E, @UnsafeVariance A>): Either<E, A> = when (this) {
        is Left -> defaultValue()
        is Right -> this
    }
}

class Left<out E, out A>(internal val value: E) : Either<E, A>() {
    override fun <B> map(f: (A) -> B): Either<E, B> = Left(value)

    override fun <B> flatMap(f: (A) -> Either<@UnsafeVariance E, B>): Either<E, B> = Left(value)

    override fun toString(): String = "Left($value)"
}

class Right<out E, out A>(internal val value: A) : Either<E, A>() {
    override fun <B> map(f: (A) -> B): Either<E, B> = Right(f(value))

    override fun <B> flatMap(f: (A) -> Either<@UnsafeVariance E, B>): Either<E, B> = f(value)

    override fun toString(): String = "Right($value)"
}
