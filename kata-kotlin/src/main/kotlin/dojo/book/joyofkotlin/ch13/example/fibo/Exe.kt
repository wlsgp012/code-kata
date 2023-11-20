package dojo.book.joyofkotlin.ch13.example.fibo

import dojo.book.joyofkotlin.ch13.AbstractActor
import dojo.book.joyofkotlin.ch13.Actor
import dojo.book.joyofkotlin.ch13.common.List
import dojo.book.joyofkotlin.ch13.common.Result
import dojo.book.joyofkotlin.ch13.common.range
import kotlinx.coroutines.sync.Semaphore

private val semaphore = Semaphore(1)
private const val listLength = 20_000
private val workers = Runtime.getRuntime().availableProcessors()
private val rnd = java.util.Random(0)
private val testList = range(0, listLength).map { rnd.nextInt(35) }
suspend fun main() {
    println(workers)
    semaphore.acquire()
    val startTime = System.currentTimeMillis()

    val client = object : AbstractActor<Result<List<Int>>>("Client") {
        override fun onReceive(
            message: Result<List<Int>>,
            sender: Result<Actor<Result<List<Int>>>>,
        ) {
            message.forEach({ processSuccess(it) }, { processFailure(it.message ?: "Unknown error") })
            println("Total time: " + (System.currentTimeMillis() - startTime))
            semaphore.release()
        }
    }

    val manager = Manager("Manager", testList, client, workers)
    manager.start()
    semaphore.acquire()
}

fun processSuccess(lst: List<Int>) {
    println("Input: ${testList.splitAt(40).first}")
    println("Result: ${lst.splitAt(40).first}")
}

private fun processFailure(message: String) {
    println(message)
}
