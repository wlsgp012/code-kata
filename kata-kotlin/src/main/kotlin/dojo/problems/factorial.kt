package dojo.problems

import java.math.BigInteger

fun factorial(n: Int): BigInteger = when (n) {
    0 -> BigInteger.ONE
    else -> BigInteger.valueOf(n.toLong()) * factorial(n - 1)
}

fun main() {
    factorial(1000).run(::println)
}