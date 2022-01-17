package dojo.double

import java.util.stream.IntStream
import kotlin.streams.toList

fun main() {
    val x: Double = 12.23
    val y: Double = 34.45
    println(x + y) // 46.68000000000001
    println(y - x) // 22.220000000000002

    val sum: Double = IntStream.range(0, 10).toList().map { 0.1 }.reduce { a, b -> a + b }
    println(sum) // 0.9999999999999999
}
