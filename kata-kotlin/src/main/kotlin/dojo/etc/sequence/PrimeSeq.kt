package dojo.etc.sequence

private fun primes(start: Int): Sequence<Int> = sequence {
    println("Starting to look")
    var index = start
    while (true) {
        if (index > 1 && (2 until index).none { i -> index % i == 0 }) {
            yield(index)
            println("Generating next after $index")
        }
        index++
    }
}

fun main() {
    for (prime in primes(start = 17)) {
        println("Received $prime")
        if (prime > 30) break
    }
}
