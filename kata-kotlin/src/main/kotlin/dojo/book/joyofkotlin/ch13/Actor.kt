package dojo.book.joyofkotlin.ch13

import dojo.joyofkotlin.ch13.common.Result

interface Actor<T> {
    val context: ActorContext<T>

    fun self(): Result<Actor<T>> = Result(this)
    fun tell(message: T, sender: Result<Actor<T>> = self())
    fun tell(message: T, sender: Actor<T>) = tell(message, Result(sender))
    fun shutdown()

    companion object {
        fun <T> noSender(): Result<Actor<T>> = Result()
    }
}