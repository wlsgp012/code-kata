package dojo.book.inaction.ch11.lambda

fun buildStringOld(builderAction: (StringBuilder) -> Unit): String {
    val sb =StringBuilder()
    builderAction(sb)
    return sb.toString()
}


fun buildString(builderAction: StringBuilder.() -> Unit): String {
    val sb =StringBuilder()
    sb.builderAction()
    return sb.toString()
}

fun main() {
    buildStringOld { sb ->
        sb.append("aa")
        sb.append("bb")
    }

    val s = buildString {
        this.append("Hello")
        append("World!")
    }
    println(s)
}

inline fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}

inline fun <T,R> with(receiver: T, block: T.() -> R): R = receiver.block()