package dojo.inaction.corutine

import kotlinx.coroutines.*
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class ScopeTest

fun now() = ZonedDateTime.now().toLocalDateTime().truncatedTo(ChronoUnit.MILLIS)

fun log(msg: String) = println("${now()}:${Thread.currentThread()}:$msg")

fun launchInGlobalScope() {
    GlobalScope.launch {
        log("coroutine started")
    }
}

fun launchInGlobalScopeWithE(error: Boolean) {
    println("before with $error")
    CoroutineScope(Dispatchers.Unconfined).launch {
        log("coroutine started with $error")
        Thread.sleep(1000L)
        if (error) throw RuntimeException("ssssss")
        log("coroutine finished with $error")
    }
    println("after with $error")
}

fun runBlockingExample() {
    runBlocking {
        launch {
            log("GlobalScope.launch started.")
        }
    }
}

fun yieldExample() {
    runBlocking {
        launch {
            log("1")
            yield()
            log("3")
            yield()
            log("5")
        }
        log("after first launch")
        launch {
            log("2")
            delay(1000L)
            log("4")
            delay(1000L)
            log("6")
        }
        log("after second launch")
    }
}

fun sumAll() {
    runBlocking {
        val d1 = async { delay(1000L); 1 }
        log("after async(d1) : $d1")
        val d2 = async { delay(2000L); 2 }
        log("after async(d2) : $d2")
        val d3 = async { delay(3000L); 3 }
        log("after async(d3) : $d3")

        log("1+2+3 = ${d1.await() + d2.await() + d3.await()}")
        log("after await all & add")
    }
}

fun sumAll2() {
    GlobalScope.launch {
        val d1 = async { delay(1000L); 1 }
        log("after async(d1) : $d1")
        val d2 = async { delay(2000L); 2 }
        log("after async(d2) : $d2")
        val d3 = async { delay(3000L); 3 }
        log("after async(d3) : $d3")

        log("1+2+3 = ${d1.await() + d2.await() + d3.await()}")
        log("after await all & add")
    }
}

fun contextExample() {
    runBlocking<Unit> {
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }
}

suspend fun yieldThreeTime() {
    log("1")
    delay(1000L)
    yield()
    log("2")
    delay(1000L)
    yield()
    log("3")
    delay(1000L)
    yield()
    log("4")
}

fun suspendExample() {
    GlobalScope.launch { yieldThreeTime() }
}

fun main() {
    log("main() started")
    try {
        launchInGlobalScopeWithE(true)
    } catch (e: Exception) {
        println("@#@#@#@#@#@#@#@#@#@#@#@#@#")
    }
    launchInGlobalScopeWithE(false)
//    runBlockingExample()
//    yieldExample()
//    sumAll()
//    sumAll2()
//    contextExample()
//    suspendExample()
//    log("example executed")
    Thread.sleep(5000L)
    log("Main() terminated")
}
