package dojo.book.joyofkotlin.ch3

/**
 * p.130 3-7
 */
fun <A, B, C> pa(a: A, f: (A) -> (B) -> C): (B) -> C = f(a)

/**
 * p.131 3-8
 */
fun <A, B, C> pb(b: B, f: (A) -> (B) -> C): (A) -> C = { a -> f(a)(b) }

/**
 * p.132 3-9
 */
// fun <A, B, C, D> func(a: A, b: B, c: C, d: D): String = "$a, $b, $c, $d"
fun <A, B, C, D> func(a: A): (B) -> (C) -> (D) -> String =
    { b ->
        { c ->
            { d ->
                "$a, $b, $c, $d"
            }
        }
    }

/**
 * p.134 3-10
 */
fun <A, B, C> currying(f: (A, B) -> C): (A) -> (B) -> C = { a -> { b -> f(a, b) } }

/**
 * p.135 3-11
 */
fun <A, B, C> flip(f: (A) -> (B) -> C): (B) -> (A) -> C = { b -> { a -> f(a)(b) } }
