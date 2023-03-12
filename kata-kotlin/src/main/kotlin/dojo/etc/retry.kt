package dojo.etc

import java.lang.RuntimeException

fun <T> retry(count: Int, f: () -> T): T {
    tailrec fun <T> process(r: Result<T>, count: Int, f: () -> T): Result<T> {
        return if (r.isSuccess || count <= 0) r else process(runCatching(f), count - 1, f)
    }
    return process(runCatching(f), count, f).getOrThrow()
}

fun main() {
    retry(3) { println("@@@@@@"); throw RuntimeException("dfdff") }
}
