package dojo.joyofkotlin.ch12

import dojo.joyofkotlin.ch5.List
import dojo.joyofkotlin.ch7.newresult.Result
import dojo.joyofkotlin.ch9.Lazy
import dojo.joyofkotlin.ch9.Stream


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

    /**
     * p.502 12-7
     */
    fun <B> flatMap(g: (A) -> IO<B>): IO<B> = IO { g(f())() }

    companion object {
        val empty: IO<Unit> = IO {}
        operator fun <A> invoke(a: A): IO<A> = IO { a }

        /**
         * p.503 12-8
         */
//        fun <A> repeat(n: Int, io: IO<A>): IO<List<A>> {
//            tailrec fun process(count: Int, result: IO<List<A>>): IO<List<A>> {
//                return if (count == 0) result.map { it.reverse2() } else process(
//                    count - 1,
//                    result.map { it.cons(io()) })
//            }
//            return process(n, IO { List() })
//        }
        fun <A, B, C> map2(ioa: IO<A>, iob: IO<B>, f: (A) -> (B) -> C): IO<C> =
            ioa.flatMap { a -> iob.map { b -> f(a)(b) } }

        fun <A> repeat(n: Int, io: IO<A>): IO<List<A>> =
            Stream.fill(n, Lazy { io })
                .foldRight(Lazy { IO { List() } }) { ioa ->
                    { listIO ->
                        map2(ioa, listIO()) { a ->
                            { aList -> aList.cons(a) }
                        }
                    }
                }

        /**
         * p.507 12-9
         */
        fun <A, B> forever(ioa: IO<A>): IO<B>{
            val f: () -> IO<B> = { forever(ioa) }
            return ioa.flatMap { f() }
        }
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
//    .map { Console.readln()() }
    .flatMap { Console.readln() }
    .map(::buildMessage)
//    .map { Console.println(it)() }
    .flatMap { Console.println(it) }

fun main() {
//    val computation = show(toString(inverse(3)))
    sayHello()()
}