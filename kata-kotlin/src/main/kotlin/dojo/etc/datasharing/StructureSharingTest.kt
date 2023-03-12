package dojo.etc.datasharing

fun main() {
    val x = listOf(1, 2, 3)
    println(x)
    val y = x + 4
    println(y)
    val z = y.dropLast(1)
    println(z)

    println(x == z)
    println(x === z)
}
