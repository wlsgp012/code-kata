package dojo.inaction.ch7

import java.lang.IndexOutOfBoundsException

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    operator fun rem(other: Point): Point = Point(x % other.x, y % other.y)
}

operator fun Point.times(scale: Double): Point = Point((x * scale).toInt(), (y * scale).toInt())

operator fun Point.unaryMinus(): Point = Point(-x, -y)

operator fun Point.get(index: Int): Int = when (index) {
    0 -> x
    1 -> y
    else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
}

data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.set(index: Int, value: Int) {
    when (index) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

fun main() {
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2)
    println(p1 % p2)
    println(p1 * 1.5)
    var point = Point(1, 2)
    point += Point(3, 4)
    println(point)
    println(point[0])
    println(point[1])

    val numbers = ArrayList<Int>()
    numbers += 42
    println(numbers)

    println(-Point(10, 20))

    val mp = MutablePoint(10, 20)
    println(mp)
    mp[1] = 42
    println(mp)
}