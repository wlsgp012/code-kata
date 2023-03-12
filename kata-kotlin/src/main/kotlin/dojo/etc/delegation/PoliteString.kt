package dojo.etc.delegation

import kotlin.reflect.KProperty

class PoliteString(var content: String) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = content.replace("stupid", "s*****")
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        content = value
    }
}

fun main() {
    var comment: String by PoliteString("Some nice message")
    println(comment)
    comment = "This is stupid"
    println(comment)
    println("length is ${comment.length}")

    fun beingpolite(content: String) = PoliteString(content)
    var com: String by beingpolite("Some nice")
//
}
