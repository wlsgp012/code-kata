package dojo.joyofkotlin.ch12.trampolining

sealed class IO<out A> {
    internal class Return<out A>(val value: A) : IO<A>()
    internal class Suspend<out A>(val resume: () -> A) : IO<A>()
    internal class Continue<A, out B>(val sub: IO<A>, val f: (A) -> IO<B>) : IO<A>()

    operator fun invoke(): A = invoke(this)

    operator fun invoke(io: IO<@UnsafeVariance A>): A {
        tailrec fun invokeHelper(io: IO<A>): A = when (io) {
            is Return -> io.value
            is Suspend -> io.resume()
            else -> {
                val ct = io as Continue<A, A>
                val sub = ct.sub
                val f = ct.f
                when (sub) {
                    is Return -> invokeHelper(f(sub.value))
                    is Suspend -> invokeHelper(f(sub.resume()))
                    else -> {
                        val ct2 = sub as Continue<A, A>
                        val sub2 = ct2.sub
                        val f2 = ct2.f
                        invokeHelper(sub2.flatMap { f2(it).flatMap(f) })
                    }
                }
            }
        }
        return invokeHelper(io)
    }

    fun <B> map(f: (A) -> B): IO<B> = flatMap { Return(f(it)) }

    fun <B> flatMap(f: (A) -> IO<B>): IO<B> = Continue(this, f) as IO<B>

    class IORef<A>(private var value: A) {
        fun set(a: A): IO<A> {
            value = a
            return unit(a)
        }

        fun get(): IO<A> = unit(value)
        fun modify(f: (A) -> A): IO<A> = get().flatMap { a -> set(f(a)) }
    }

    companion object {
        val empty: IO<Unit> = IO.Suspend { Unit }
        internal fun <A> unit(a: A): IO<A> = IO.Suspend { a }
    }

}