package dojo.joyofkotlin.ch9

import dojo.joyofkotlin.ch5.List
import dojo.joyofkotlin.ch7.newresult.Result
import kotlin.random.Random
import dojo.joyofkotlin.ch8.sequence as sequenceR
import dojo.joyofkotlin.ch8.traverse as traverseR

/**
 * p.355 9-1
 */
class Lazy<out A>(f: () -> A) : () -> A {
    private val c: A by lazy(f)

    /**
     * p.362 9-6
     */
    fun <B> map(h: (A) -> B): Lazy<B> = Lazy { h(c) }

    /**
     * p.363 9-7
     */
    fun <B> flatmap(h: (A) -> Lazy<B>): Lazy<B> = Lazy { h(c)() }

    /**
     * p.370 9-10
     */
    fun forEach(condition: Boolean, ifTrue: (A) -> Unit, ifFalse: () -> Unit = {}) {
        if(condition)
            ifTrue(c)
        else
            ifFalse()
    }
    fun forEach(condition: Boolean, ifTrue: () -> Unit = {}, ifFalse: (A) -> Unit) {
        if(condition)
            ifTrue()
        else
            ifFalse(c)
    }
    fun forEach(condition: Boolean, ifTrue: (A) -> Unit, ifFalse: (A) -> Unit) {
        if(condition)
            ifTrue(c)
        else
            ifFalse(c)
    }

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

/**
 * p.365 9-8
 */
fun <A> sequence(xs: List<Lazy<A>>): Lazy<List<A>> = Lazy { xs.map { it() } }

/**
 * p.366 9-9
 */
fun <A> sequenceResult(xs: List<Lazy<A>>): Lazy<Result<List<A>>> = Lazy { sequenceR(xs.map { Result(it()) }) }
fun <A> sequenceResult2(xs: List<Lazy<A>>): Lazy<Result<List<A>>> = Lazy { traverseR(xs) { Result(it()) } }

fun main() {
    println("9-1 =======================")
    test9_1()
    println("9-2 =======================")
    test9_2()
    println("9-7 =======================")
    test9_7()
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

private fun test9_7() {
    val getGreetings = { println("getGreetings") }
    val greetings = Lazy { getGreetings() }
    val flatGreets: (String) -> Lazy<String> = { name: String -> greetings.map { "$it, $name!" } }
    val name: Lazy<String> = Lazy {
        println("computing name")
        "Mickey"
    }
    val message = name.flatmap(flatGreets)
    println(message)
}
