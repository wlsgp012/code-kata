package dojo.book.joyofkotlin.ch13

import dojo.book.joyofkotlin.ch13.common.Result

interface ActorContext<T> {
    fun behavior(): MessageProcessor<T>
    fun become(behavior: MessageProcessor<T>)
}

interface MessageProcessor<T> {
    fun process(message: T, sender: Result<Actor<T>>)
}
