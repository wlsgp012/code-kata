package dojo.book.coroutines.deepdive.ch2

import java.math.BigInteger

val fibonacci: Sequence<BigInteger> = sequence {
    var first = 0.toBigInteger()
    var second = 1.toBigInteger()
    while (true) {
        yield(first)

        val temp = second
        second += first
        first = temp
    }
}

fun main() {
    print(fibonacci.take(10).toList())
}
