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
}
