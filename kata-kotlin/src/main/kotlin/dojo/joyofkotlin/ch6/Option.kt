package dojo.joyofkotlin.ch6

sealed class Option<out A> {
    abstract fun isEmpty(): Boolean

    internal object None : Option<Nothing>() {
        override fun isEmpty(): Boolean = true

        override fun equals(other: Any?): Boolean = other === None

        override fun hashCode(): Int = 0

        override fun toString(): String = "Nne"
    }

    internal data class Some<out A>(internal val value: A) : Option<A>() {
        override fun isEmpty(): Boolean = false
    }

    companion object {
        operator fun <A> invoke(a: A? = null): Option<A> =
            when (a) {
                null -> None
                else -> Some(a)
            }
    }

    /**
     * p.252 6-1
     */
    fun getOrElse(default: @UnsafeVariance A): A = when (this) {
        is None -> default
        is Some -> value
    }

    /**
     * p.253 6-2
     */
    fun getOrElse(defaultFn: () -> @UnsafeVariance A): A = when (this) {
        is None -> defaultFn()
        is Some -> value
    }

    /**
     * p.254 6-3
     */
    fun <B> map(f: (A) -> B): Option<B> = when (this) {
        is None -> None
        is Some -> Some(f(value))
    }

    /**
     * p.255 6-4
     */
    fun <B> flatMap_(f: (A) -> Option<B>): Option<B> = when (this) {
        is None -> None
        is Some -> f(value)
    }

    fun <B> flatMap(f: (A) -> Option<B>): Option<B> = map(f).getOrElse(None)

    /**
     * p.255 6-5
     */
    fun orElse(default: () -> Option<@UnsafeVariance A>): Option<A> = map { this }.getOrElse(default)

    /**
     * p.257 6-6
     */
    fun filter(p: (A) -> Boolean): Option<A> = flatMap { if(p(it)) Some(it) else None }
}
