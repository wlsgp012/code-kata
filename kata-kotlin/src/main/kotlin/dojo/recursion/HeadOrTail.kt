package dojo.recursion

tailrec fun tailRecursion(n: Int): Int = when (n) {
    0 -> 0
    else -> tailRecursion(n - 1)
}

fun headRecursion(n: Int) {
    if (n > 0) {
        headRecursion(n - 1)
        println("in head : $n")
    }
}

fun main() {
    println(tailRecursion(10))
    println(headRecursion(10))
}
