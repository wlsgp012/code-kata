package dojo.joyofkotlin.ch13.common

import dojo.joyofkotlin.ch13.common.Stream.Companion.fill
import dojo.joyofkotlin.ch13.common.Stream.Companion.iterate
import java.math.BigInteger


sealed class Stream<out A> {

    abstract fun isEmpty(): Boolean

    abstract fun head(): Result<A>

    abstract fun tail(): Result<Stream<A>>

    abstract fun takeAtMost(n: Int): Stream<A>

    abstract fun takeWhile(p: (A) -> Boolean): Stream<A>

    abstract fun <B> foldRight(z: Lazy<B>,
                               f: (A) -> (Lazy<B>) -> B): B

    fun find(p: (A) -> Boolean): Result<A> = filter(p).head()

    fun <B> flatMap(f: (A) -> Stream<B>): Stream<B> =
        foldRight(Lazy { Empty as Stream<B> }) { a ->
            { b: Lazy<Stream<B>> ->
                f(a).append(b)
            }
        }

    fun append(stream2: Lazy<Stream<@UnsafeVariance A>>): Stream<A> =
        this.foldRight(stream2) { a: A ->
            { b: Lazy<Stream<A>> ->
                Stream.cons(Lazy { a }, b)
            }
        }

    fun filter(p: (A) -> Boolean): Stream<A>  =
        dropWhile { x -> !p(x) }.let { stream ->
            when (stream) {
                Empty -> stream
                is Cons -> cons(stream.hd, Lazy { stream.tl().filter(p)})
            }
        }

    fun filter2(p: (A) -> Boolean): Stream<A> =
        dropWhile { x -> !p(x) }.let { stream ->
            when (stream) {
                Empty -> stream
                is Cons -> stream.head().map { a ->
                    cons(Lazy { a }, Lazy { stream.tl().filter(p) })
                }.getOrElse(Empty)
            }
        }

    fun <B> map(f: (A) -> B): Stream<B> =
        foldRight(Lazy { Empty }) { a ->
            { b: Lazy<Stream<B>> ->
                cons(Lazy { f(a) }, b)
            }
        }

    fun headSafeViaFoldRight(): Result<A> =
        foldRight(Lazy { Result<A>() }) { a -> { Result(a) } }

    fun takeWhileViaFoldRight(p: (A) -> Boolean): Stream<A> =
        foldRight(Lazy { Empty }) { a ->
            { b: Lazy<Stream<A>> ->
                if (p(a))
                    cons(Lazy { a }, b)
                else
                    Empty
            }
        }

    fun exists(p: (A) -> Boolean): Boolean = exists(this, p)

    fun dropWhile(p: (A) -> Boolean): Stream<A> = dropWhile(this, p)

    fun toList(): List<A> = toList(this)

    fun dropAtMost(n: Int): Stream<A> = dropAtMost(n, this)

    private object Empty: Stream<Nothing>() {

        override fun <B> foldRight(z: Lazy<B>, f: (Nothing) -> (Lazy<B>) -> B): B = z()

        override fun takeWhile(p: (Nothing) -> Boolean): Stream<Nothing> = this

        override fun takeAtMost(n: Int): Stream<Nothing> = this

        override fun head(): Result<Nothing> = Result()

        override fun tail(): Result<Nothing> = Result()

        override fun isEmpty(): Boolean = true

    }

    private class Cons<out A> (internal val hd: Lazy<A>,
                               internal val tl: Lazy<Stream<A>>) : Stream<A>() {

        override fun <B> foldRight(z: Lazy<B>, f: (A) -> (Lazy<B>) -> B): B =
            f(hd())(Lazy { tl().foldRight(z, f) })

        override fun takeWhile(p: (A) -> Boolean): Stream<A> = when {
            p(hd()) -> cons(hd, Lazy { tl().takeWhile(p) })
            else -> Empty
        }

        override fun takeAtMost(n: Int): Stream<A> = when {
            n > 0 -> cons(hd, Lazy { tl().takeAtMost(n - 1) })
            else -> Empty
        }

        override fun head(): Result<A> = Result(hd())

        override fun tail(): Result<Stream<A>> = Result(tl())

        override fun isEmpty(): Boolean = false
    }

    companion object {

        fun <A> cons(hd: Lazy<A>, tl: Lazy<Stream<A>>): Stream<A> = Cons(hd, tl)

        operator fun <A> invoke(): Stream<A> = Empty

        operator fun <A> invoke(array: Array<A>): Stream<A> = invoke(List(*array))

        operator fun <A> invoke(list: List<A>): Stream<A> {
            return when (list) {
                List.Nil -> invoke()
                is List.Cons -> cons(Lazy { list.head }, Lazy { invoke(list.tail)})
            }
        }

        tailrec fun <A> dropWhile(stream: Stream<A>,
                                  p: (A) -> Boolean): Stream<A> = when (stream) {
            Empty -> stream
            is Cons -> when {
                p(stream.hd()) -> dropWhile(stream.tl(), p)
                else -> stream
            }
        }

        tailrec fun <A> dropAtMost(n: Int, stream: Stream<A>): Stream<A> =  when {
            n > 0 -> when (stream) {
                Empty -> stream
                is Cons -> dropAtMost(n - 1, stream.tl())
            }
            else -> stream
        }

        fun <A> toList(stream: Stream<A>) : List<A> {
            tailrec fun <A> toList(list: List<A>, stream: Stream<A>) : List<A> = when (stream) {
                Empty -> list
                is Cons -> toList(list.cons(stream.hd()), stream.tl())
            }
            return toList(List(), stream).reverse()
        }

        fun <A> iterate(seed: Lazy<A>, f: (A) -> A): Stream<A> = cons(seed, Lazy { iterate(f(seed()), f) })

        fun <A> iterate(seed: A, f: (A) -> A): Stream<A> = iterate(Lazy { seed }, f)

        fun <A> fill(n: Int, elem: Lazy<A>): Stream<A> {
            tailrec fun <A> fill(acc: Stream<A>, n: Int, elem: Lazy<A>): Stream<A> {
                //println(elem)
                return when {
                    n <= 0 -> acc
                    else -> fill(Cons(elem, Lazy { acc }), n - 1, elem)
                }
            }
            return fill(Empty, n, elem)
        }

        fun <A> fill2(n: Int, elem: Lazy<A>): Stream<A> {
            //println(elem)
            return when {
                n <= 0 -> Empty
                else -> Cons(elem, Lazy { fill(n - 1, elem) })
            }
        }

        tailrec fun <A> exists(stream: Stream<A>, p: (A) -> Boolean): Boolean =
            when (stream) {
                Empty -> false
                is Cons  -> when {
                    p(stream.hd()) -> true
                    else           -> exists(stream.tl(), p)
                }
            }

        fun <A, S> unfold(z: S, f: (S) -> Result<Pair<A, S>>): Stream<A> =
            f(z).map { x ->
                Stream.cons(Lazy { x.first }, Lazy { unfold(x.second, f) })
            }.getOrElse(Stream.Empty)

        fun from(n: Int): Stream<Int> = unfold(n) { x -> Result(Pair(x, x + 1)) }

        fun range(start: Int, end: Int): Stream<Int> = when {
            start > end -> Stream.invoke()
            else        -> cons(Lazy { start }, Lazy { range(start + 1, end) })
        }

        fun range(start: BigInteger, end: BigInteger): Stream<BigInteger> = when {
            start > end -> Stream.invoke()
            else        -> cons(Lazy { start }, Lazy { range(start + BigInteger.ONE, end) })
        }
    }
}