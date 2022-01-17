package dojo.coroutine

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private suspend fun task1() {
    println("start task1 in Thread ${Thread.currentThread()}")
    yield()
    println("end task1 in Thread ${Thread.currentThread()}")
}

private suspend fun task2() {
    println("start task2 in Thread ${Thread.currentThread()}")
    yield()
    println("end task2 in Thread ${Thread.currentThread()}")
}

fun main() {
    Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
        .asCoroutineDispatcher().use { context ->
            println("start")
            runBlocking {
                withContext(Dispatchers.Default){ task1()}
                launch(Dispatchers.Default + CoroutineName("task runner")) { task2() }
                println("called task1 and task2 from ${Thread.currentThread()}")
            }
        }
}
