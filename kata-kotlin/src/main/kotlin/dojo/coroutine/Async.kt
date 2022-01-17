package dojo.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val count = async(Dispatchers.Default) {
            println("fetching in ${Thread.currentThread()}")
            Runtime.getRuntime().availableProcessors()
        }
        println("Called the function in ${Thread.currentThread()}")
        println("Number of cores is ${count.await()}")
    }
}
