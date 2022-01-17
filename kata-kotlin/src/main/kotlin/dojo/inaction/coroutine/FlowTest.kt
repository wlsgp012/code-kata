package dojo.inaction.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import java.io.IOException

class FlowTest

fun getString() = flow {
    delay(1000)
    emit("str")
}

fun printFlow() = CoroutineScope(Dispatchers.Default).launch {
    getString().collect { print(it) }
}

fun xxx(scope: CoroutineScope) = scope.launch {
    launch { launch { launch { throw IOException() } } }
}

fun main() {
//    println("before")
//    printFlow()
//    println("after")
//    Thread.sleep(2000)

    try {
        val runBlocking = runBlocking {
            getString().single()
        }
        println(runBlocking)
    } catch (e: Exception) {
        println("external try-catch is working!! $e")
    }
    println("End main.")
}
