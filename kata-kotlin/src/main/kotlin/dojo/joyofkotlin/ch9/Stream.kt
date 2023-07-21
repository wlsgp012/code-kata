package dojo.joyofkotlin.ch9

import dojo.joyofkotlin.ch7.newresult.Result

sealed class Stream<out A> {
    abstract fun isEmpty(): Boolean
    abstract fun head(): Result<A>
    abstract fun tail(): Result<Stream<A>>

    /**
     * p.377 9-12
     */
    fun takeAtMost(n: Int): Stream<A> =
        when (this) {
            is Empty -> this
            is Cons ->
                if (n > 0) cons(hd, Lazy { tl().takeAtMost(n - 1) })
                else Empty
        }

    /**
     * p.377 9-13
     */
    fun dropAtMost(n: Int): Stream<A> =
        when (this) {
            is Empty -> this
            is Cons -> {
                if (n > 0) tl().dropAtMost(n - 1)
                else this
            }
        }

    private object Empty : Stream<Nothing>() {
        override fun isEmpty(): Boolean = true

        override fun head(): Result<Nothing> = Result()

        override fun tail(): Result<Stream<Nothing>> = Result()
    }

    private class Cons<out A>(val hd: Lazy<A>, val tl: Lazy<Stream<A>>) : Stream<A>() {
        override fun isEmpty(): Boolean = false

        override fun head(): Result<A> = Result(hd())

        override fun tail(): Result<Stream<A>> = Result(tl())
    }

    companion object {
        fun <A> cons(hd: Lazy<A>, tl: Lazy<Stream<A>>): Stream<A> = Cons(hd, tl)
        operator fun <A> invoke(): Stream<A> = Empty
        fun from(i: Int): Stream<Int> = cons(Lazy { i }, Lazy { from(i + 1) })

        /**
         * p.376 9-11
         */
        fun <A> repeat(f: () -> A): Stream<A> = cons(Lazy { f() }, Lazy { repeat(f) })
    }
}


fun main() {
    test_()
    test_13()
}

private fun test_() {
    val stream = Stream.from(1)
    stream.head().forEach(::println)
    stream.tail().flatMap { it.head() }.forEach(::println)
    stream.tail().flatMap { it.tail().flatMap { it.head() } }.forEach(::println)
    stream.head().forEach(::println)
    stream.tail().flatMap { it.head() }.forEach(::println)
    stream.tail().flatMap { it.tail().flatMap { it.head() } }.forEach(::println)
}

private fun test_13() {
    val stream = Stream.from(1).takeAtMost(10)
    stream.dropAtMost(5).head().forEach(::println)
}
