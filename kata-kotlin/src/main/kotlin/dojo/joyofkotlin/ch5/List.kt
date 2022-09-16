package dojo.joyofkotlin.ch5

sealed class List<A> {
    abstract fun isEmpty(): Boolean

    private object Nil : List<Nothing>() {
        override fun isEmpty(): Boolean = true
        override fun toString(): String = "[NIL]"
    }

    private class Cons<A>(internal val head: A, internal val tail: List<A>) : List<A>() {
        override fun isEmpty(): Boolean = false
        override fun toString(): String = "[${toString("", this)}NIL]"
        private tailrec fun toString(acc: String, list: List<A>): String =
            when (list) {
                Nil -> acc
                is Cons -> toString("$acc${list.head}, ", list.tail)
            }
    }

    /**
     * p.207 5-1
     */
    fun cons(e: A): List<A> = Cons(e, this)

    /**
     * p.208 5-2
     */
    fun setHead(e: A): List<A> =
        when (this) {
            Nil -> this
//            is Cons -> Cons(e, this.tail)
            is Cons -> this.tail.cons(e)
        }

    companion object {
        operator fun <A> invoke(vararg az: A): List<A> =
            az.foldRight(Nil as List<A>) { a: A, list: List<A> -> Cons(a, list) }
    }
}