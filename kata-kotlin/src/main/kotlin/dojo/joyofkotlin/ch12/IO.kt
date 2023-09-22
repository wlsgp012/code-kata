package dojo.joyofkotlin.ch12

import dojo.joyofkotlin.ch7.newresult.Result


class IO<out A>(private val f: () -> A) {
    operator fun invoke() = f()

    /**
     * p.498 12-4
     */
    operator fun plus(io: IO<@UnsafeVariance A>): IO<A> = IO {
        f()
        io.f()
    }

    /**
     * p.501 12-6
     */
    fun <B> map(g: (A) -> B): IO<B> = IO { g(f()) }

    companion object {
        val empty: IO<Unit> = IO {}
        operator fun <A> invoke(a: A): IO<A> = IO { a }
    }
}

fun show(message: String): IO<Unit> = IO { println(message) }

fun <A> toString(rd: Result<A>): String = rd.map { it.toString() }.getOrElse { rd.toString() }

fun inverse(i: Int): Result<Double> = when (i) {
    0 -> Result.failure("Div by 0")
    else -> Result(1.0 / i)
}

private fun buildMessage(name: String): String = "Hello, $name"

private fun sayHello() = Console.print("Enter your name: ")
    .map { Console.readln()() }
    .map(::buildMessage)
    .map { Console.println(it)() }

fun main() {
//    val computation = show(toString(inverse(3)))
    sayHello()()
}