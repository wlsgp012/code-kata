package dojo.book.joyofkotlin.ch4

import dojo.inaction.ch3.joinToString
import java.math.BigInteger
import java.util.*
import java.util.Collections.emptyList

fun hello() {
    println("Hello")
    hello()
}

fun sum_old(n: Int): Int {
    var s = 0
    var i = 0
    while (i <= n) {
        s += i
        i += 1
    }
    return s
}

fun sum(n: Int): Int {
    tailrec fun sum(s: Int, i: Int): Int = if (i > n) s else sum(s + i, i + 1)
    return sum(0, 0)
}

/**
 * p.156 4-1
 */
fun inc(n: Int) = n + 1
fun dec(n: Int) = n - 1
fun add(a: Int, b: Int): Int = if (b == 0) a else add(inc(a), dec(b))

/**
 * p.161 4-2
 */
object Factorial {
    val factorial: (Int) -> Int by lazy {
        { n: Int -> if (n <= 1) n else n * factorial(n - 1) }
    }
}

fun fact(n: Int): BigInteger {
    tailrec fun fact(x: BigInteger, r: BigInteger): BigInteger =
        if (x > n.toBigInteger()) r else fact(x + BigInteger.ONE, r * x)
    return fact(BigInteger.ONE, BigInteger.ONE)
}

fun f(x: BigInteger, r: BigInteger): BigInteger {
    return x + r
}

/**
 * p.168 4-3
 */
fun fibo(number: Int): BigInteger {
    tailrec fun go(n: Int, a: BigInteger, b: BigInteger): BigInteger {
        return if (n == number) a
        else go(n + 1, a + b, a)
    }
    return go(0, BigInteger.ONE, BigInteger.ZERO)
}

fun <T> makeString(list: List<T>, delim: String): String =
    when {
        list.isEmpty() -> ""
        else -> "${list.first()}$delim${makeString(list.drop(1), delim)}"
    }

fun <T> List<T>.head(): T =
    if (this.isEmpty())
        throw java.lang.IllegalArgumentException("head called on empty list")
    else
        this[0]

fun <T> List<T>.tail(): List<T> =
    if (this.isEmpty())
        throw IllegalArgumentException("tail called on empty list")
    else
        this.drop(1)

/**
 * p.170 4-4
 */
fun <T> makeString2(list: List<T>, delim: String): String {
    tailrec fun go(l: List<T>, r: String): String {
        return if (l.isEmpty()) r else go(l.tail(), "$r$delim${l.head()}")
    }
    return go(list, "")
}

fun <T> makeString3(list: List<T>, delim: String): String {
    return foldLeft(list, "") { a, b -> "$a$delim$b" }
}

/**
 * p.171 4-5
 */
tailrec fun <T, R> foldLeft(l: List<T>, r: R, f: (R, T) -> R): R =
    if (l.isEmpty()) r else foldLeft(l.tail(), f(r, l.head()), f)

fun prepend(c: Char, s: String): String = "$c$s"

/**
 * p.172 4-6
 */
fun <T, R> foldRight(l: List<T>, r: R, f: (T, R) -> R): R =
    if (l.isEmpty()) r else f(l.head(), foldRight(l.tail(), r, f))

/**
 * p.174 4-7
 */
fun <T> reverseViaFoldLeft(l: List<T>): List<T> {
    return foldLeft(l, emptyList()) { r, t -> listOf(t) + r }
}

fun <T> reverseViaFoldRight(l: List<T>): List<T> {
    return foldRight(l, emptyList()) { t, r -> r + t }
}

/**
 * p.175 4-8
 */
fun <T> reverseViaFoldLeft2(l: List<T>): List<T> {
    return foldLeft(l, listOf()) { r, t -> foldLeft(r, listOf(t)) { a, b -> a + b } }
}

/**
 * p.177 4-10
 */
fun <T> unfold(seed: T, f: (T) -> T, p: (T) -> Boolean): List<T> {
    tailrec fun go(v: T, l: List<T>): List<T> {
        return if (p(v)) go(f(v), l + v)
        else l
    }
    return go(seed, emptyList())
}

/**
 * p.176 4-9
 */
fun range(start: Int, end: Int): List<Int> {
    tailrec fun go(n: Int, l: List<Int>): List<Int> {
        return if (n == end) l
        else go(inc(n), l + n)
    }
    return go(start, emptyList())
}

/**
 * p.177 4-11
 */
fun rangeViaUnfold(start: Int, end: Int): List<Int> = unfold(start, ::inc) { v -> end > v }

fun <T> prepend(list: List<T>, elem: T): List<T> = listOf(elem) + list

/**
 * p.178 4-12
 */
fun range2(start: Int, end: Int): List<Int> =
    if (start >= end) listOf()
    else prepend(range2(inc(start), end), start)

/**
 * p.178 4-13
 */
fun <T> unfold2(seed: T, f: (T) -> T, p: (T) -> Boolean): List<T> =
    if (!p(seed)) listOf()
    else prepend(unfold2(f(seed), f, p), seed)

/**
 * p.179 4-14
 */
fun <T> unfold3(seed: T, f: (T) -> T, p: (T) -> Boolean): List<T> {
    tailrec fun go(acc: List<T>, t: T): List<T> =
        if (!p(t)) acc
        else go(prepend(acc, t), f(t))
    return go(listOf(), seed)
}

/**
 * p.182 4-15
 */
fun fiboAsString(number: Int): String {
    val c = mutableListOf<BigInteger>(BigInteger.ONE)
    tailrec fun go(n: Int, a: BigInteger, b: BigInteger): BigInteger {
        return if (n == number) a
        else {
            val next = a + b
            c.add(next)
            go(n + 1, next, a)
        }
    }

    go(0, BigInteger.ONE, BigInteger.ZERO)
    return c.joinToString()
}

/**
 * p.184 4-16
 */
fun <T> iterate(seed: T, f: (T) -> T, n: Int): List<T> {
    tailrec fun go(v: T, l: List<T>): List<T> {
        return if (l.size < n) go(f(v), l + v)
        else l
    }
    return go(seed, emptyList())
}

/**
 * p.185 4-17
 */
fun <T, U> map(list: List<T>, f: (T) -> U): List<U> = foldLeft(list, listOf()) { r, a -> r + f(a) }

/**
 * p.186 4-18
 */
fun fiboCorecursive(number: Int): String {
    val pairs = iterate(Pair(1, 1), { p -> (p.second to (p.first + p.second)) }, number)
    return map(pairs) { p -> p.first }.joinToString()
}
fun main() {
//    hello()
//    println(sum_old(10))
//    println(sum(10))
//    println(add(4, 3))
//    println(fact(0))
//    println(fact(1))
//    println(fact(2))
//    println(fact(3))
//    println(fact(4))
//    println(fact(100000))
//    println(Factorial.factorial(4))
//    println(fibo(0))
//    println(fibo(1))
//    println(fibo(2))
//    println(fibo(3))
//    println(fibo(4))
//    println(fibo(5))
//    println(fibo(10000).toString().length)
//    println(reverseViaFoldLeft(listOf(1, 2, 3)))
//    println(reverseViaFoldLeft2(listOf(1, 2, 3)))
//    println(reverseViaFoldRight(listOf(1, 2, 3)))
//    println(range(1, 5))
//    println(rangeViaUnfold(1, 5))
    println(fiboAsString(10))
    println(fiboCorecursive(10))
}
