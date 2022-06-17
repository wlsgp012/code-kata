package dojo.joyofkotlin.ch4

import java.math.BigInteger

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
    tailrec fun fact(x: BigInteger, r: BigInteger): BigInteger = if (x > n.toBigInteger()) r else fact(x + BigInteger.ONE, r * x)
    return fact(BigInteger.ONE, BigInteger.ONE)
}

fun f(x: BigInteger, r: BigInteger): BigInteger{
    return x + r
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
    println(Factorial.factorial(4))
}
