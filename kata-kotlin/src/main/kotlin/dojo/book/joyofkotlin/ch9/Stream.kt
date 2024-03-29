package dojo.book.joyofkotlin.ch9

import dojo.book.joyofkotlin.ch5.List
import dojo.book.joyofkotlin.ch7.newresult.Result
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

    /**
     * p.382 9-18
     */
    fun dropWhile(p: (A) -> Boolean): Stream<A> = dropWhile(this, p)

    /**
     * p.383 9-19
     */
    fun exists(p: (A) -> Boolean): Boolean = exists(this, p)

    /**
     * p.384 9-20
     */
    fun <B> foldRight(z: Lazy<B>, f: (A) -> (Lazy<B>) -> B): B = when (this) {
        is Empty -> z()
        is Cons -> f(hd())(Lazy { tl().foldRight(z, f) })
    }

    /**
     * p.385 9-21
     */
    fun takeWhileViaFoldRight(p: (A) -> Boolean): Stream<A> =
        foldRight(Lazy { Empty }) { a -> { acc: Lazy<Stream<A>> -> if (p(a)) cons(Lazy { a }, acc) else Empty } }

    /**
     * p.386 9-22
     */
    fun headSafeViaFoldRight(): Result<A> = foldRight(Lazy { Result() }) { a -> { Result(a) } }

    /**
     * p.386 9-23
     */
    fun <B> map(f: (A) -> B): Stream<B> =
        foldRight(Lazy { Empty }) { a -> { acc: Lazy<Stream<B>> -> cons(Lazy { f(a) }, acc) } }

    /**
     * p.387 9-24
     */
    fun filter(p: (A) -> Boolean): Stream<A> =
        foldRight(Lazy { Empty }) { a -> { acc: Lazy<Stream<A>> -> if (p(a)) cons(Lazy { a }, acc) else acc() } }

    /**
     * p.393 9-30
     */
    fun filter2(p: (A) -> Boolean): Stream<A> =
        dropWhile { x -> !p(x) }.let { stream ->
            when (stream) {
                is Empty -> stream
                is Cons -> cons(stream.hd, Lazy { stream.tl().filter2(p) })
            }
        }

    /**
     * p.387 9-25
     */
    fun append(stream2: Lazy<Stream<@UnsafeVariance A>>): Stream<A> =
        foldRight(stream2) { a -> { acc -> cons(Lazy { a }, acc) } }

    /**
     * p.388 9-26
     */
    fun <B> flatMap(f: (A) -> Stream<B>): Stream<B> =
        foldRight(Lazy { Empty }) { a -> { acc: Lazy<Stream<B>> -> f(a).append(acc) } }

    /**
     * p.390 9-27
     */
    fun find(p: (A) -> Boolean): Result<A> = filter(p).head()

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


        /**
         * p.382 9-18
         */
        tailrec fun <A> dropWhile(stream: Stream<A>, p: (A) -> Boolean): Stream<A> = when (stream) {
            is Empty -> stream
            is Cons -> if (p(stream.hd())) dropWhile(stream.tl(), p) else stream
        }

        /**
         * p.383 9-19
         */
        tailrec fun <A> exists(stream: Stream<A>, p: (A) -> Boolean): Boolean = when (stream) {
            is Empty -> false
            is Cons -> if (p(stream.hd())) true else exists(stream.tl(), p)
        }

        /**
         * p.391 9-29
         */
        fun <A, S> unfold(z: S, f: (S) -> Result<Pair<A, S>>): Stream<A> {
//            return when (val a: Result<Pair<A, S>> = f(z)) {
//                is Result.Empty -> invoke()
//                is Result.Failure -> invoke()
//                is Result.Success -> cons(Lazy(a.value::first), Lazy { unfold(a.value.second, f) })
//            }
            return f(z).map { a -> cons(Lazy(a::first), Lazy { unfold(a.second, f) }) }.getOrElse { Empty }
        }

        fun fromViaUnfold(i: Int): Stream<Int> = unfold(i) { Result.invoke(it to it + 1) }

        /**
         * p.503 12-8
         */
        fun <A> fill(n: Int, elem: Lazy<A>): Stream<A>{
            tailrec fun <A> process(acc: Stream<A>, n: Int, elem: Lazy<A>): Stream<A> = when{
                n <=0 -> acc
                else -> process(Cons(elem, Lazy { acc }), n-1, elem)
            }
            return process(Empty, n, elem)
        }
    }
}

/**
 * p.391 9-28
 */
fun fibo(): Stream<Int> {
    return Stream.iterate(1 to 1) { (a, b) -> b to a + b }.map { (a, _) -> a }
}

fun fiboViaUnfold(): Stream<Int> {
    return Stream.unfold(1 to 1) { (a, b) -> Result.invoke(a to (b to a + b)) }
}

fun main() {
    test_()
    println()
    test_13()
    println()
    test_14()
    println()
//    test_15()
    println()
    test_6_2()
    println()
    test_28()
    println()
    test_29()
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


private val f = { x: Int ->
    val y = x * 3
    println("Mapping $x to $y")
    y
}

private val p = { x: Int ->
    println("Filtering $x")
    x % 2 == 0
}

fun test_6_2() {
    val list = List(1, 2, 3, 4, 5).map(f).filter(p).map(f)
    println(list)
    println()
    val stream = Stream.from(1).takeAtMost(5).map(f).filter(p).map(f)
    println(stream.toList())
}

fun test_28() {
    fibo().takeAtMost(10).toList().run(::println)
    fiboViaUnfold().takeAtMost(10).toList().run(::println)
}

fun test_29() {
    Stream.from(0).takeAtMost(30).toList().run(::println)
}