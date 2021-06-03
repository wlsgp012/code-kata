package dojo.inaction.ch4

sealed interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
    }

fun main() {
//    println(eval(Sum(Num(1), Num(4))))
    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(strings.toTypedArray()))

}
