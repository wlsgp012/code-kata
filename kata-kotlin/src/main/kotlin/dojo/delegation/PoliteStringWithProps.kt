package dojo.delegation

import kotlin.reflect.KProperty

class PoliteStringWithProps(val dataSource: MutableMap<String, Any>) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) =
        (dataSource[property.name] as? String)?.replace("stupid", "s*****") ?: ""

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        dataSource[property.name] = value
    }
}

class PostComment(dataSource: MutableMap<String, Any>) {
    val title: String by dataSource
    var likes: Int by dataSource
    val comment: String by PoliteStringWithProps(dataSource)
    override fun toString(): String = "Title: $title Likes: $likes Comment: $comment"
}

fun main() {
    val data: List<MutableMap<String, Any>> = listOf(
        mutableMapOf(
            "title" to "Using Delegation",
            "likes" to 2,
            "comment" to "Keep it simple, stupid"
        ),
        mutableMapOf<String,Any>(
            "title" to "Using Inheritance",
            "likes" to 1,
            "comment" to "Prefer Delegation where possible"
        )
    )
    val forPost1 = PostComment(data[0])
    val forPost2 = PostComment(data[1])
    forPost1.likes++
    println(forPost1)
    println(forPost2)
}
