package dojo.joyofkotlin.ch9

import kotlin.random.Random

/**
 * p.355 9-1
 */
class Lazy<out A>(f: () -> A) : () -> A {
    private val c: A by lazy(f)
    override operator fun invoke(): A = c
}

fun or(a: Lazy<Boolean>, b: Lazy<Boolean>): Boolean = if (a()) true else b()

/**
 * p.356 9-2
 */
fun constructMessage(greetings: Lazy<String>, name: Lazy<String>): Lazy<String> {
    return Lazy { "${greetings()}, ${name()}" }
}

val greetings = Lazy {
    println("Evaluating greetings")
    "Hello"
}

val name = Lazy {
    println("Computing name")
    "Mickey"
}

/**
 * p.358 9-3
 */
val constructMessage2 = { greetings: Lazy<String> ->
    { name: Lazy<String> ->
        { Lazy { "${greetings()}, ${name()}" } }
    }
}

/**
 * p.359 9-4,5
 */
fun <A, B, C> lift2(f: (A) -> (B) -> C): (Lazy<A>) -> (Lazy<B>) -> Lazy<C> = { la ->
    { lb -> Lazy { f(la())(lb()) } }
}

fun main() {
    println("9-1 =======================")
    test9_1()
    println("9-2 =======================")
    test9_2()

}

private fun test9_1() {
    val first = Lazy {
        println("Evaluating first")
        true
    }
    val second = Lazy {
        println("Evaluating second")
        throw IllegalStateException()
    }
    println(first() || second())
    println(first() || second())
    println(or(first, second))
}

private fun test9_2() {
    val msg = constructMessage(greetings, name)
    val condition = Random(System.currentTimeMillis()).nextInt() % 2 == 0
    println(if (condition) msg() else "No")
    println(if (condition) msg() else "No")
}
