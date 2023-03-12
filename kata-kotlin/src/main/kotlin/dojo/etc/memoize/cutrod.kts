import Fibdelegate.Memoize

val prices = mapOf(1 to 2, 2 to 4, 3 to 6, 4 to 7, 5 to 10, 6 to 17, 7 to 17)
val maxPrice: (Int) -> Int by Memoize { length: Int ->
    val priceAtLength = prices.getOrDefault(length, 0)
    (1 until length).fold(priceAtLength) { max, cutLength ->
        val cutPrice = maxPrice(cutLength) + maxPrice(length - cutLength)
        Math.max(cutPrice, max)
    }
}
for (i in 1..7) {
    println("For length $i max price is ${maxPrice(i)}")
}
