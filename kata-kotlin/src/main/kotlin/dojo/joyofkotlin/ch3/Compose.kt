package dojo.joyofkotlin.ch3

/**
 * p.116 3-1
 */
fun compose(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int = { a -> f(g(a)) }

/**
 * p.117 3-2
 */
fun <T, U, V> compose2(f: (U) -> V, g: (T) -> U): (T) -> V = { a -> f(g(a)) }

/**
 * p.118 3-3
 */
val add: (Int) -> (Int) -> Int = { a -> { b -> a + b } }
