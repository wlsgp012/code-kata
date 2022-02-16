package dojo.problems

// https://4clojure.oxal.org/#/problem/53
fun main() {
    check(sol(listOf(1, 0, 1, 2, 3, 0, 4, 5)) == listOf(0, 1, 2, 3))
    check(sol(listOf(5, 6, 1, 3, 2, 7)) == listOf(5, 6))
    check(sol(listOf(2, 3, 3, 4, 5)) == listOf(3, 4, 5))
    check(sol(listOf(7, 6, 5, 4)) == listOf<Int>())
}

fun sol(xs: List<Int>): List<Int> {
    val r = mutableListOf<List<Int>>()
    xs.forEachIndexed { i: Int, v: Int ->
        if (xs.size == i + 1) {
            return@forEachIndexed
        }
        if (xs[i + 1] - v == 1) {
            r.add(listOf(v, xs[i + 1]))
        } else {
            r.add(listOf())
        }
    }
    val r2 = r.fold(emptyList<List<Int>>()) { acc, xs ->
        when {
            acc.isEmpty() -> {
                listOf(xs)
            }
            xs.isEmpty() -> {
                acc + listOf(xs)
            }
            else -> {
                acc.dropLast(xs.size - 1) + listOf((acc.last() + xs))
            }
        }
    }.reduce { x, y -> if (x.size > y.size) x else y }
    return r2.distinct()
}
