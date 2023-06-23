package dojo.joyofkotlin.ch5

import dojo.joyofkotlin.ch5.List.Companion.foldLeft
import dojo.joyofkotlin.ch5.List.Cons
import dojo.joyofkotlin.ch5.List.Empty
import dojo.joyofkotlin.ch7.Result
import java.util.concurrent.ExecutorService

sealed class List<A> {
    abstract fun isEmpty(): Boolean

    fun concat(list: List<A>): List<A> = concatViaRight(this, list)

    abstract class Empty<A> : List<A>() {
        //        override fun concat(list: List<A>): List<A> = list
        override fun isEmpty(): Boolean = true
    }

    private object Nil : Empty<Nothing>() {
        override fun lengthMemoized(): Int = 0
        override fun headSafe(): Result<Nothing> = Result()
    }

    class Cons<A>(val head: A, val tail: List<A>) : List<A>() {
        private val length: Int = tail.lengthMemoized() + 1
        override fun lengthMemoized(): Int = length
        override fun headSafe(): Result<A> = Result.invoke(head)

        override fun isEmpty(): Boolean = false

//        override fun concat(list: List<A>): List<A> = Cons(this.head, list.concat(this.tail))

        override fun toString(): String = "[${toString("", this)}NIL]"
        private tailrec fun toString(acc: String, list: List<A>): String =
            when (list) {
                is Empty -> acc
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
            is Empty -> this
            is Cons -> this.tail.cons(e)
        }

    fun drop2(n: Int): List<A> =
        when (this) {
            is Empty -> this
            is Cons -> if (n == 0) this else this.tail.drop2(n - 1)
        }

    fun drop(n: Int): List<A> = drop(this, n)

    /**
     * p.214 5-4
     */
    fun dropWhile(p: (A) -> Boolean): List<A> = dropWhile(this, p)

//    fun concat(xs: List<A>): List<A> = concat(this, xs)

    fun reverse(): List<A> = reverse(invoke(), this)

    /**
     * p.217 5-5
     */
    fun init(): List<A> =
        when (this) {
            is Empty -> this
            is Cons -> (if (tail is Nil) tail else Cons(head, tail.init())) as List<A>
        }

    fun init2(): List<A> = reverse().drop(1).reverse()

    fun <B> foldRight(identity: B, f: (A) -> (B) -> B): B = Companion.foldRight(this, identity, f)

    fun <B> foldLeft(acc: B, f: (B) -> (A) -> B): B = Companion.foldLeft(acc, this, f)

    fun <B> foldLeft(acc: B, p: (B) -> Boolean, f: (B) -> (A) -> B): B = Companion.foldLeft(acc, this, p, f)

    /**
     * p.224 5-8
     */
    fun length(): Int = foldRight(0) { { it + 1 } }

    /**
     * p.307 8-1
     */
    abstract fun lengthMemoized(): Int

    /**
     * p.309 8-2
     */
    abstract fun headSafe(): Result<A>

    /**
     * p.310 8-3
     */
//    fun lastSafe(): Result<A> = this.reverse().headSafe()
    fun lastSafe(): Result<A> = foldLeft(Result()) { _: Result<A> -> { y: A -> Result(y) } }

    /**
     * p.311 8-4
     */
    // pattern matching could be used
    fun headSafe2(): Result<A> = foldRight(Result()) { x -> { _ -> Result(x) } }

    /**
     * p.318 8-11
     */
    fun <A1, A2> unzip(f: (A) -> Pair<A1, A2>): Pair<List<A1>, List<A2>> =
        foldRightViaLeft(Pair(invoke(), invoke())) { a ->
            { (a1List, a2List) ->
                f(a).let { (a1, a2) -> a1List.cons(a1) to a2List.cons(a2) }
            }
        }

    /**
     * p.319 8-12
     */
    fun getAt(index: Int): Result<A> =
        Pair(Result.failure<A>("Index out of bound"), index).let {
            if (index < 0 || index >= length()) it
            else foldLeft(it) { acc ->
                { a ->
                    if (acc.second < 0) acc
                    else Pair(Result(a), acc.second - 1)
                }
            }
        }.first

    fun getAt_(index: Int): Result<A> {
        tailrec fun <A> process(list: Cons<A>, i: Int): Result<A> =
            if (index == 0) Result(list.head)
            else process(list.tail as Cons, i - 1)
        return if (index < 0 || index >= length()) Result.failure("Index out of bound")
        else process(this as Cons, index)
    }

    // not tail recursion
    fun getAt__(index: Int): Result<A> {
        tailrec fun <A> process(list: Cons<A>, i: Int): Result<A> =
            (list as Cons).let {
                if (index == 0) Result(list.head)
                else process(list.tail as Cons, i - 1)
            }
        return if (index < 0 || index >= length()) Result.failure("Index out of bound")
        else process(this as Cons, index)
    }

    /**
     * p.322 8-13
     */
    fun getAt_13(index: Int): Result<A> =
        Pair(Result.failure<A>("Index out of bound"), index).let {
            if (index < 0 || index >= length()) it
            else {
                foldLeft(it, { acc -> acc.second < 0 }) { acc ->
                    { a -> Pair(Result(a), acc.second - 1) }
                }
            }
        }.first

    /**
     * p.326 8-14
     */
    fun splitAt_my(index: Int): Result<Pair<List<A>, List<A>>> {
        tailrec fun <A> process(target: Cons<A>, result: List<A>, count: Int): Pair<List<A>, List<A>> =
            if (count == 0) result.reverse2() to target
            else process(target.tail as Cons, result.cons(target.head), count - 1)

        return if (index < 0 || index >= length()) Result.failure("Index out of bound")
        else Result(process(this as Cons, invoke(), index))
    }

    fun splitAt_14(index: Int): Pair<List<A>, List<A>> {
        tailrec fun process(acc: List<A>, list: List<A>, i: Int): Pair<List<A>, List<A>> =
            when (list) {
                is Empty -> Pair(list.reverse2(), acc)
                is Cons -> {
                    if (i == 0) Pair(list.reverse2(), acc)
                    else process(acc.cons(list.head), list.tail, i - 1)
                }
            }
        return when {
            index < 0 -> splitAt_14(0)
            index > length() -> splitAt_14(length())
            else -> process(invoke(), this.reverse2(), this.length() - index)
        }
    }

    /**
     * p.327 8-15
     */
    fun splitAt(index: Int): Pair<List<A>, List<A>> =
        when {
            index < 0 -> invoke<A>() to this
            index > length() -> this to invoke()
            else -> foldLeft(
                Triple(index, invoke<A>(), this),
                { (i, acc, target) -> i == 0 || target.isEmpty() }) { (i, acc, target) ->
                { x -> Triple(i - 1, acc.cons(x), (target as Cons).tail) }
            }.run { this.second.reverse2() to this.third }
        }

    /**
     * p.330 8-16
     */
    fun startWith(sub: List<@UnsafeVariance A>): Boolean {
        tailrec fun process(list: List<A>, s: List<A>): Boolean = when (s) {
            is Empty -> true
            is Cons -> when (list) {
                is Empty -> false
                is Cons -> if (list.head == s.head) process(list.tail, s.tail) else false
            }
        }
        return process(this, sub)
    }

    fun hasSubList(sub: List<@UnsafeVariance A>): Boolean {
        tailrec fun process(list: List<A>, s: List<A>): Boolean = when (list) {
            is Empty -> s.isEmpty()
            is Cons -> if (list.startWith(s)) true else process(list.tail, s)
        }
        return process(this, sub)
    }

    /**
     * p.331 8-17
     */
    fun <B> groupBy(f: (A) -> B): Map<B, List<A>> =
        foldLeft(mapOf()) { m ->
            { x ->
                val b = f(x)
                val n = m.getOrDefault(b, invoke()).cons(x)
                m + (b to n)
            }
        }

    /**
     * p.335 8-19
     */
    fun exists(p: (A) -> Boolean): Boolean = foldLeft(false, { it }) { _ -> { x -> p(x) } }

    /**
     * p.336 8-21
     */
//    fun forAll(p: (A) -> Boolean): Boolean = foldLeft(true, { !it }) { r -> { x -> r && p(x) }}
    fun forAll(p: (A) -> Boolean): Boolean = !exists { !p(it) }

    /**
     * p.338 8-22
     */
    fun divide(depth: Int): List<List<A>> {
        tailrec fun process(list: List<List<A>>, d: Int): List<List<A>> {
            return when (list) {
                is Empty -> list
                is Cons -> {
                    if (list.head.length() < 2 || d < 1) {
                        list
                    } else {
                        val xr = list.flatMap { it.splitListAt(it.length() / 2) }
                        process(xr, d - 1)
                    }
                }
            }
        }
        return if (isEmpty()) List(this) else process(List(this), depth)
    }

    /**
     * p.340 8-23
     */
    fun <B> parFoldLeft(es: ExecutorService, init: B, f: (B) -> (A) -> B, m: (B) -> (B) -> B): Result<B> =
        try {
            divide(1024)
                .map { xs: List<A> -> es.submit<B> { xs.foldLeft(init, f) } }
                .map { futureB -> futureB.get() }
                .run {
                    Result(this.foldLeft(init, m))
                }
        } catch (e: Exception) {
            Result.failure(e)
        }

    fun <B> parMap(es: ExecutorService, g: (A) -> B): Result<List<B>> =
        try {
            map { x -> es.submit<B> { g(x) } }
                .map { it.get() }
                .run { Result(this) }
        } catch (e: Exception) {
            Result.failure(e)
        }

    fun splitListAt(index: Int): List<List<A>> {
        val (a, b) = splitAt(index)
        return List(a, b)
    }

    /**
     * p.231 5-11
     */
    fun reverse2(): List<A> = foldLeft(invoke()) { acc -> { Cons(it, acc) } }

    /**
     * p.231 5-12
     */
    fun <B> foldLeftViaRight(acc: B, f: (B) -> (A) -> B): B =
        foldRight({ p: B -> p }) { x -> { r -> { z -> r(f(z)(x)) } } }(acc)

    fun <B> foldRightViaLeft(identity: B, f: (A) -> (B) -> B): B =
        foldLeft({ p: B -> p }) { r -> { x -> { z -> r(f(x)(z)) } } }(identity)

    /**
     * p.236 5-18
     */
    fun <B> map(f: (A) -> B): List<B> = foldRightViaLeft(invoke()) { x -> { r -> Cons(f(x), r) } }

    /**
     * p.236 5-19
     */
    fun filter(p: (A) -> Boolean): List<A> = foldRightViaLeft(invoke()) { x -> { r -> if (p(x)) Cons(x, r) else r } }

    /**
     * p.237 5-20
     */
    fun <B> flatMap(f: (A) -> List<B>): List<B> = foldRightViaLeft(invoke()) { x -> { r -> f(x).concat(r) } }
    fun <B> flatMap2(f: (A) -> List<B>): List<B> = flatten(map(f))

    /**
     * p.237 5-21
     */
    fun filter2(p: (A) -> Boolean): List<A> = flatMap { x -> if (p(x)) invoke(x) else invoke() }

    companion object {
        operator fun <A> invoke(vararg az: A): List<A> =
            az.foldRight(Nil as List<A>) { a: A, list: List<A> -> Cons(a, list) }

        tailrec fun <A> drop(l: List<A>, n: Int): List<A> =
            when (l) {
                is Empty -> l
                is Cons -> if (n == 0) l else drop(l.tail, n - 1)
            }

        /**
         * p.214 5-4
         */
        tailrec fun <A> dropWhile(l: List<A>, p: (A) -> Boolean): List<A> =
            when (l) {
                is Empty -> l
                is Cons -> if (p(l.head)) dropWhile(l, p) else l
            }

//        fun <A> concat(xs: List<A>, ys: List<A>): List<A> =
//            when (xs) {
//                Nil -> ys
//                is Cons -> Cons(xs.head, concat(xs.tail, ys))
// //                is Cons -> concat(xs.tail, ys).cons(xs.head)
//            }

        tailrec fun <A> reverse(acc: List<A>, list: List<A>): List<A> =
            when (list) {
                Nil -> acc
                is Cons -> reverse(acc.cons(list.head), list.tail)
                else -> throw IllegalStateException()
            }

        fun <A, B> foldRight(list: List<A>, identity: B, f: (A) -> (B) -> B): B =
            when (list) {
                is Empty -> identity
                is Cons -> f(list.head)(foldRight(list.tail, identity, f))
            }

        tailrec fun <A, B> foldLeft(acc: B, list: List<A>, f: (B) -> (A) -> B): B =
            when (list) {
                is Empty -> acc
                is Cons -> foldLeft(f(acc)(list.head), list.tail, f)
            }

        fun <A, B> foldLeft(acc: B, list: List<A>, p: (B) -> Boolean, f: (B) -> (A) -> B): B =
            when (list) {
                is Empty -> acc
                is Cons -> if (p(acc)) acc else foldLeft(f(acc)(list.head), list.tail, p, f)
            }

        /**
         * p.233 5-14
         */
        fun <A> concatViaRight(xs: List<A>, ys: List<A>): List<A> = xs.foldRight(ys) { x -> { r -> Cons(x, r) } }

        fun <A> concatViaLeft(xs: List<A>, ys: List<A>): List<A> = xs.reverse().foldLeft(ys) { r -> r::cons }

        /**
         * p.234 5-15
         */
        fun <A> flatten(xs: List<List<A>>): List<A> = xs.foldRight(Nil as List<A>) { x -> { r -> x.concat(r) } }
    }
}

/**
 * p.218 5-6
 */
fun sum(ints: List<Int>): Int =
    when (ints) {
        is Empty -> 0
        is Cons -> ints.head + sum(ints.tail)
    }

/**
 * p.224 5-7
 */
fun product(ds: List<Double>): Double =
    when (ds) {
        is Empty -> 1.0
        is Cons -> ds.head * product(ds.tail)
    }

fun <A, B> foldRight(list: List<A>, identity: B, f: (A) -> (B) -> B): B =
    when (list) {
        is Empty -> identity
        is Cons -> f(list.head)(foldRight(list.tail, identity, f))
    }

fun sumByFoldRight(ints: List<Int>): Int = foldRight(ints, 0) { x -> { r -> x + r } }
fun productByFoldRight(ds: List<Double>): Double = foldRight(ds, 1.0) { x -> { r -> x * r } }

tailrec fun <A, B> foldLeft(acc: B, list: List<A>, f: (B) -> (A) -> B): B =
    when (list) {
        is Empty -> acc
        is Cons -> foldLeft(f(acc)(list.head), list.tail, f)
    }

/**
 * p.230 5-10
 */
fun sumByFoldLeft(ints: List<Int>): Int = foldLeft(0, ints) { acc -> { x -> acc + x } }
fun productByFoldLeft(ds: List<Double>): Double = foldLeft(1.0, ds) { acc -> { x -> acc * x } }
fun lengthByFoldLeft(xs: List<*>): Int = foldLeft(0, xs) { acc -> { acc + 1 } }

/**
 * p.235 5-16
 */
fun triple(xs: List<Int>): List<Int> = xs.foldRight(List()) { x -> { r -> Cons(x * 3, r) } }

fun doubleToString(xs: List<Double>): List<String> = xs.foldRight(List()) { x -> { r -> Cons(x.toString(), r) } }

fun main() {
    val list = (1..30000).fold(List(0)) { l, i -> l.cons(i) }
//    val drop = list.drop2(9999)
    val drop = list.drop(30000)
    println(drop)

    val a = List(1, 2, 3)
    val b = List(4, 5, 6)
    val concat = a.concat(b)
    println(concat)

    println(List(1, 2, 3, 4).init())
    println(sum(List(1, 2, 3, 4)))

    println("==================")
    val x = List(6, 5, 4)
    x.foldLeft(0) { z -> { x -> z - x } }.run(::println)
    x.foldRight(0) { x -> { z -> x - z } }.run(::println)
    x.foldLeftViaRight(0) { z -> { x -> z - x } }.run(::println)
    x.foldRightViaLeft(0) { x -> { z -> x - z } }.run(::println)
    println("==================")
    List.concatViaLeft(a, b).run(::println)
    List.concatViaRight(a, b).run(::println)

    println("==================")
    println(a.lastSafe())
    println(List<Int>().lastSafe())

    println("==================")
    val xx = (1..3000).fold(List(0)) { l, i -> l.cons(i) }.reverse2()
    println(xx.getAt(0))
    println(xx.getAt_13(0))

    println("==================")
    val c = List(1, 2, 3, 4)
    println(c.splitAt_14(2))
    println(c.splitAt_my(2))
    println(c.splitAt(2))
    println(c.splitAt_14(0))
    println(c.splitAt_my(0))
    println(c.splitAt(0))
    println(c.exists { it > 5 })
    println(c.exists { it == 3 })
    println(c.forAll { it < 5 })
    println(c.forAll { it < 3 })

    println("==================")
    val xs = (1..10).fold(List(0)) { l, i -> l.cons(i) }
    println(xs.splitListAt(5))
    println(xs.divide(3))
}
