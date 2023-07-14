package dojo.joyofkotlin.ch9

import dojo.joyofkotlin.ch7.newresult.Result

sealed class Stream<out A> {
    abstract fun isEmpty(): Boolean
    abstract fun head(): Result<A>
    abstract fun tail(): Result<Stream<A>>

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
    }
}

fun main() {
    val stream = Stream.from(1)
    stream.head().forEach(::println)
    stream.tail().flatMap { it.head() }.forEach(::println)
    stream.tail().flatMap { it.tail().flatMap { it.head() }}.forEach(::println)
    stream.head().forEach(::println)
    stream.tail().flatMap { it.head() }.forEach(::println)
    stream.tail().flatMap { it.tail().flatMap { it.head() }}.forEach(::println)
}