package dojo.inaction.ch3

const val UNIX_LINE_SEPARATOR = "\n"

fun <T> Collection<T>.joinToString(separator: String = ", ", prefix: String = "", postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun Collection<String>.join(separator: String = ", ", prefix: String = "", postfix: String = "") =
    joinToString(separator, prefix, postfix)

fun String.lastChar(): Char = get(this.length - 1)

val String.lastChar2: Char get() = get(this.length - 1)

var StringBuilder.lastChar: Char
    get() = get(this.length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

fun main() {
    val list = listOf(1, 2, 3)
    println(list.joinToString("; ", "(", ")"))
    println(list.joinToString())
    println(listOf("one", "two").join())
    println("Father".lastChar())
    println("Father".lastChar2)
    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)
}
