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
    list.foldRight(Result(List())){ ra -> { acc -> map2(ra, acc){ a -> { xs -> xs.cons(a)}} }}