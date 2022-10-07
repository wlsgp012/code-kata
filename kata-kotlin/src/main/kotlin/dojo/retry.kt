package dojo

fun <T> retry(count: Int, f: () -> T): T {
    tailrec fun <T> process(r: Result<T>, count: Int, f: () -> T): Result<T> {
        return if (r.isSuccess || count <= 0) r else process(runCatching(f), count - 1, f)
    }
    return process(runCatching(f), count, f).getOrThrow()
}

fun main() {
    retry(3) { println("@@@@@@"); throw java.lang.RuntimeException("dfdff") }
}