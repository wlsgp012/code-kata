package dojo.joyofkotlin.ch8

import dojo.joyofkotlin.ch5.List
import dojo.joyofkotlin.ch7.newresult.Result
import dojo.joyofkotlin.ch7.newresult.map2

/**
 * p.312 8-5
 */
fun <A> flattenResult(list: List<Result<A>>): List<A> = list.flatMap { x -> x.map { List(it) }.getOrElse(List()) }
/*
fun <A> flattenResult(list: List<Result<A>>): List<A> {
    return list.foldRight(List()) { x ->
        { acc ->
            when (x) {
                is Result.Failure -> acc
                is Result.Success -> acc.cons(x.value)
            }
        }
    }
}
 */

/**
 * p.313 8-6
 */
fun <A> sequence(list: List<Result<A>>): Result<List<A>> =
    list.foldRight(Result(List())) { ra -> { acc -> map2(ra, acc) { a -> { xs -> xs.cons(a) } } } }

/**
 * p.314 8-7
 */
fun <A, B> traverse(list: List<A>, f: (A) -> Result<B>): Result<List<B>> =
    list.foldRight(Result(List())) { a -> { acc -> map2(f(a), acc) { a -> { xs -> xs.cons(a) } } } }

fun <A> sequence_via_traverse(list: List<Result<A>>): Result<List<A>> = traverse(list) { it }

/**
 * p.315 8-8
 */
fun <A, B, C> zipWith(xs: List<A>, ys: List<B>, f: (A) -> (B) -> C): List<C> {
    tailrec fun process(result: List<C>, xss: List<A>, yss: List<B>): List<C> =
        when (xss) {
            is List.Empty -> result
            is List.Cons -> when (yss) {
                is List.Empty -> result
                is List.Cons -> process(result.cons(f(xss.head)(yss.head)), xss.tail, yss.tail)
            }
        }
    return process(List(), xs, ys).reverse()
}

/**
 * p.317 8-9
 */
fun <A, B, C> product(xs: List<A>, ys: List<B>, f: (A) -> (B) -> C): List<C> =
    xs.flatMap { x -> ys.map { y -> f(x)(y) } }

/**
 * p.318 8-10
 */
fun <A, B> unzip(xs: List<Pair<A, B>>): Pair<List<A>, List<B>> =
    xs.foldRightViaLeft(Pair(List(), List())) { (a, b) ->
        { (listA, listB) ->
            listA.cons(a) to listB.cons(b)
        }
    }
fun <A, B> unzip_(xs: List<Pair<A, B>>): Pair<List<A>, List<B>> = xs.unzip { it }

