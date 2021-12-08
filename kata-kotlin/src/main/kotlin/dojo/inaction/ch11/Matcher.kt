package dojo.inaction.ch11

interface Matcher<T> {
    fun test(value: T)
}

class startWith(val prefix: String) : Matcher<String> {
    override fun test(value: String) {
        if (!value.startsWith(prefix)) throw AssertionError("no way")
    }
}
