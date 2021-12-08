package dojo.inaction.corutine

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn

interface Generator<out R, in T> {
    fun next(param: T): R?
}

@RestrictsSuspension
interface GeneratorBuilder<in T, R> {
    suspend fun yield(value: T): R
    suspend fun yieldAll(generator: Generator<T, R>, param: R)
}

internal class GeneratorCoroutine<T, R> : Generator<T, R>, GeneratorBuilder<T, R>,
    Continuation<Unit> {
    lateinit var nextStep: (R) -> Unit
    private var lastValue: T? = null
    private var lastException: Throwable? = null

    override fun next(param: R): T? {
        nextStep(param)
        lastException?.let { throw it }
        return lastValue
    }

    override suspend fun yield(value: T): R = suspendCoroutineUninterceptedOrReturn { cont ->
        lastValue = value
        nextStep = { param -> cont.resume(param) }
        COROUTINE_SUSPENDED
    }

    override suspend fun yieldAll(generator: Generator<T, R>, param: R): Unit =
        suspendCoroutineUninterceptedOrReturn sc@{ cont ->
            lastValue = generator.next(param)
            if (lastValue == null) return@sc Unit
            nextStep = { param ->
                lastValue = generator.next(param)
                if (lastValue == null) cont.resume(Unit)
            }
            COROUTINE_SUSPENDED
        }

    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    override fun resumeWith(result: Result<Unit>) {
        result
            .onSuccess { lastValue = null }
            .onFailure { lastException = it }
    }


}

fun <T, R> generate(block: suspend GeneratorBuilder<T, R>.(R) -> Unit): Generator<T, R> {
    val coroutine = GeneratorCoroutine<T, R>()
    val initial: suspend (R) -> Unit = { result -> block(coroutine, result) }
    coroutine.nextStep = { param -> initial.startCoroutine(param, coroutine) }
    return coroutine
}

fun idMaker () = generate<Int, Unit> {
    var index = 0
    while (index < 3)
        yield(index++)
}