package dojo.joyofkotlin.ch9

import dojo.joyofkotlin.ch5.List
import dojo.joyofkotlin.ch7.newresult.Result
import kotlin.random.Random

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
    fun dropAtMost_13(n: Int): Stream<A> =
        when (this) {
            is Empty -> this
            is Cons -> {
                if (n > 0) tl().dropAtMost_13(n - 1)
                else this
            }
        }

    fun dropAtMost(n: Int): Stream<A> = dropAtMost(n, this)

    /**
     * p.379 9-15
     */
    fun toList(): List<@UnsafeVariance A> {
        tailrec fun process(s: Stream<A>, acc: List<A>): List<A> = when (s) {
            is Empty -> acc
            is Cons -> process(s.tl(), acc.cons(s.hd()))
        }
        return process(this, List()).reverse()
    }

    /**
     * p.382 9-17
     */
    fun takeWhile(p: (A) -> Boolean): Stream<A> = when (this) {
        is Empty -> this
        is Cons -> {
            if (p(hd())) cons(hd, Lazy { tl().takeWhile(p) })
            else Empty
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

        //        fun from(i: Int): Stream<Int> = cons(Lazy { i }, Lazy { from(i + 1) })
        fun from(i: Int): Stream<Int> = iterate(i) { it + 1 }

        /**
         * p.376 9-11
         */
        fun <A> repeat(f: () -> A): Stream<A> = cons(Lazy { f() }, Lazy { repeat(f) })

        /**
         * p.378 9-14
         */
        tailrec fun <A> dropAtMost(n: Int, stream: Stream<A>): Stream<A> = when {
            n > 0 -> when (stream) {
                is Empty -> stream
                is Cons -> dropAtMost(n - 1, stream.tl())
            }

            else -> stream
        }

        /**
         * p.380 9-16
         */
        fun <A> iterate(seed: A, f: (A) -> A): Stream<A> = cons(Lazy { seed }, Lazy { iterate(f(seed), f) })


    }
}


fun main() {
    test_()
    println()
    test_13()
    println()
    test_14()
    println()
    test_15()
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

val rnd = Random(1)

private fun random(): Int {
    val r = rnd.nextInt()
    println("evaluating $r")
    return r
}

private fun test_14() {
//   val stream =  Stream.repeat(::random).takeAtMost(60000)
    val stream = Stream.repeat(::random).dropAtMost(60000)
    stream.head().forEach(::println)

}

fun test_15() {
    val s = Stream.from(0).dropAtMost(60000).takeAtMost(60000)
    println(s.toList())
}
