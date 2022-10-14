package dojo.joyofkotlin.ch5

import dojo.joyofkotlin.ch5.List.*

sealed class List<A> {
    abstract fun isEmpty(): Boolean

    abstract fun concat(list: List<A>): List<A>

    abstract class Empty<A> : List<A>() {
        override fun concat(list: List<A>): List<A> = list
        override fun isEmpty(): Boolean = true
    }

    private object Nil : Empty<Nothing>()
    class Cons<A>(val head: A, val tail: List<A>) : List<A>() {
        override fun isEmpty(): Boolean = false
        override fun concat(list: List<A>): List<A> = Cons(this.head, list.concat(this.tail))

        override fun toString(): String = "[${toString("", this)}NIL]"
        private tailrec fun toString(acc: String, list: List<A>): String =
            when (list) {
                is Empty -> acc
                is Cons -> toString("$acc${list.head}, ", list.tail)
            }
    }

    /**
     * p.207 5-1
     */
    fun cons(e: A): List<A> = Cons(e, this)

    /**
     * p.208 5-2
     */
    fun setHead(e: A): List<A> =
        when (this) {
            is Empty -> this
            is Cons -> this.tail.cons(e)
        }

    fun drop2(n: Int): List<A> =
        when (this) {
            is Empty -> this
            is Cons -> if (n == 0) this else this.tail.drop2(n - 1)
        }

    fun drop(n: Int): List<A> = drop(this, n)

    /**
     * p.214 5-4
     */
    fun dropWhile(p: (A) -> Boolean): List<A> = dropWhile(this, p)

//    fun concat(xs: List<A>): List<A> = concat(this, xs)

    fun reverse(): List<A> = reverse(List.invoke(), this)

    /**
     * p.217 5-5
     */
    fun init(): List<A> =
        when (this) {
            is Empty -> this
            is Cons -> (if (tail is Nil) tail else Cons(head, tail.init())) as List<A>
        }

    fun init2(): List<A> = reverse().drop(1).reverse()

    companion object {
        operator fun <A> invoke(vararg az: A): List<A> =
            az.foldRight(Nil as List<A>) { a: A, list: List<A> -> Cons(a, list) }

        tailrec fun <A> drop(l: List<A>, n: Int): List<A> =
            when (l) {
                is Empty -> l
                is Cons -> if (n == 0) l else drop(l.tail, n - 1)
            }

        /**
         * p.214 5-4
         */
        tailrec fun <A> dropWhile(l: List<A>, p: (A) -> Boolean): List<A> =
            when (l) {
                is Empty -> l
                is Cons -> if (p(l.head)) dropWhile(l, p) else l
            }

//        fun <A> concat(xs: List<A>, ys: List<A>): List<A> =
//            when (xs) {
//                Nil -> ys
//                is Cons -> Cons(xs.head, concat(xs.tail, ys))
// //                is Cons -> concat(xs.tail, ys).cons(xs.head)
//            }

        tailrec fun <A> reverse(acc: List<A>, list: List<A>): List<A> =
            when (list) {
                Nil -> acc
                is Cons -> reverse(acc.cons(list.head), list.tail)
                else -> throw IllegalStateException()
            }
    }
}

/**
 * p.218 5-6
 */
fun sum(ints: List<Int>): Int =
    when (ints) {
        is Empty -> 0
        is Cons -> ints.head + sum(ints.tail)
    }



fun main() {
    val list = (1..30000).fold(List(0)) { l, i -> l.cons(i) }
//    val drop = list.drop2(9999)
    val drop = list.drop(30000)
    println(drop)

    val concat = List(1, 2, 3).concat(List(4, 5, 6))
    println(concat)

    println(List(1, 2, 3, 4).init())
    println(sum(List(1, 2, 3, 4)))
}
