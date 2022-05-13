package dojo.joyofkotlin.ch3

/**
 * p.116 3-1
 */
fun compose(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int = { a -> f(g(a)) }

/**
 * p.117 3-2
 */
fun <A, B, C> compose2(f: (B) -> C, g: (A) -> B): (A) -> C = { a -> f(g(a)) }

/**
 * p.118 3-3
 */
val add: (Int) -> (Int) -> Int = { a -> { b -> a + b } }

/**
 * p.120 3-4
 */
// val compose: (f: (Int) -> Int, g: (Int) -> Int) -> (Int) -> Int = { f, g -> { a -> f(g(a)) } }
val compose3: ((Int) -> Int) -> ((Int) -> Int) -> (Int) -> Int = { f -> { g -> { a -> f(g(a)) } } }

/**
 * p.122 3-5
 */
fun <A, B, C> getCompose(): ((B) -> C) -> ((A) -> B) -> (A) -> C = { f -> { g -> { a -> f(g(a)) } } }

/**
 * p.123 3-6
 */
fun <A, B, C> higherAndThen(): ((A) -> B) -> ((B) -> C) -> (A) -> C = { f -> { g -> { a -> g(f(a)) } } }

fun par(f: (Int) -> Int): Int = f(3) + 1

fun main() {
    val add1 = { a: Int -> a + 1 }
    val multiply2 = { a: Int -> a * 2 }
//    val r = compose3(add1)(multiply2)(2)
//    val r = getCompose<Int, Int, Int>()(add1)(multiply2)(2)
//    val r = getCompose<Int, Int, Int>()(){ a: Int -> a + 1 }(multiply2)
//    val r = getCompose<Int, Int, Int>()() { a: Int -> a + 1 }() { a: Int -> a * 2 }
    val r = ((getCompose<Int, Int, Int>()){ a: Int -> a + 1 }){ a: Int -> a * 2 }(2)
    println(r)

    par() { a -> a * 2 }
    par { a -> a * 2 }
}
