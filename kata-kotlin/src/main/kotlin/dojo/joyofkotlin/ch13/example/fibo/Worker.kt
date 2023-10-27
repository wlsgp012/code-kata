package dojo.joyofkotlin.ch13.example.fibo

import dojo.joyofkotlin.ch13.AbstractActor
import dojo.joyofkotlin.ch13.Actor
import dojo.joyofkotlin.ch13.common.Result

class Worker(id: String) : AbstractActor<Int>(id) {
    override fun onReceive(message: Int, sender: Result<Actor<Int>>) {
        sender.forEach(onSuccess = {
            it.tell(slowFibonacci(message), self())
        })
    }

    private fun slowFibonacci(number: Int): Int {
        return when (number) {
            0 -> 1
            1 -> 1
            else -> slowFibonacci(number - 1) + slowFibonacci(number - 2)
        }
    }
}
