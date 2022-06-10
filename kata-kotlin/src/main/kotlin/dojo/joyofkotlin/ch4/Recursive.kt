package dojo.joyofkotlin.ch4

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
fun add(a: Int, b: Int): Int = if(b == 0) a else add(inc(a), dec(b))

fun main() {
//    hello()
//    println(sum_old(10))
//    println(sum(10))
    println( add(4, 3))
}
