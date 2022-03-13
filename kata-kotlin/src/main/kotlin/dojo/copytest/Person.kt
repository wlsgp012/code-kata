package dojo.copytest

data class Person(val name: String, val nickName: List<String>)

fun main() {
    val driss = Person("driss", listOf("monkey", "squirrel", "bird"))
    val copy = driss.copy()
//    val copy = driss.copy(nickName = driss.nickName.toList())
    println(driss.nickName === copy.nickName)
    println(driss.nickName == copy.nickName)
    println("${driss.nickName}, ${copy.nickName}")
}
