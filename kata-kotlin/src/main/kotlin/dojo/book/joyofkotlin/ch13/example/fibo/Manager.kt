package dojo.book.joyofkotlin.ch13.example.fibo

import dojo.joyofkotlin.ch13.AbstractActor
import dojo.joyofkotlin.ch13.Actor
import dojo.joyofkotlin.ch13.MessageProcessor
import dojo.joyofkotlin.ch13.common.List
import dojo.joyofkotlin.ch13.common.Result
import dojo.joyofkotlin.ch13.common.sequence

class Manager(id: String, list: List<Int>, private val client: Actor<Result<List<Int>>>, private val workers: Int) :
    AbstractActor<Int>(id) {
    private val initial: List<Pair<Int, Int>>
    private val workList: List<Int>
    private val resultList: List<Int>
    private val managerFunction: (Manager) -> (Behavior) -> (Int) -> Unit

    init {
        val splitLists = list.splitAt(this.workers)
        this.initial = splitLists.first.zipWithPosition()
        this.workList = splitLists.second
        this.resultList = List()
        managerFunction = { manager: Manager ->
            { behavior ->
                { i ->
                    val result = behavior.resultList.cons(i)
                    if (result.length == list.length) {
                        this.client.tell(Result(result))
                    } else {
                        manager.context.become(Behavior(behavior.workList.tailSafe().getOrElse(List()), result))
                    }
                }
            }
        }
    }

    inner class Behavior(
        internal val workList: List<Int>,
        internal val resultList: List<Int>,
    ) : MessageProcessor<Int> {
        override fun process(
            message: Int,
            sender: Result<Actor<Int>>,
        ) {
            managerFunction(this@Manager)(this@Behavior)(message)
            sender.forEach(onSuccess = { a: Actor<Int> ->
                workList.headSafe()
                    .forEach({ a.tell(it, self()) }) { a.shutdown() }
            })
        }
    }

    override fun onReceive(message: Int, sender: Result<Actor<Int>>) {
        context.become(Behavior(workList, resultList))
    }

    fun start() {
        onReceive(0, self())
        sequence(initial.map { this.initWorker(it) })
            .forEach(
                onSuccess = { this.initWorkers(it) },
                onFailure = { this.tellClientEmptyResult(it.message ?: "Unknown error") },
            )
    }

    private fun initWorker(t: Pair<Int, Int>): Result<() -> Unit> =
        Result({ Worker("Worker " + t.second).tell(t.first, self()) })

    private fun initWorkers(lst: List<() -> Unit>) {
        lst.forEach { it() }
    }

    private fun tellClientEmptyResult(string: String) {
        client.tell(Result.failure("$string caused by empty input list."))
    }
}
