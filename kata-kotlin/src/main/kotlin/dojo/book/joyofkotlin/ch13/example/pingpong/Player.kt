package dojo.book.joyofkotlin.ch13.example.pingpong

import dojo.book.joyofkotlin.ch13.AbstractActor
import dojo.book.joyofkotlin.ch13.Actor
import dojo.book.joyofkotlin.ch13.common.Result
import kotlinx.coroutines.sync.Semaphore

class Player(id: String, private val sound: String, private val referee: Actor<Int>) : AbstractActor<Int>(id) {
    override fun onReceive(message: Int, sender: Result<Actor<Int>>) {
        println("$sound - $message")
        if (message >= 10) {
            referee.tell(message, sender)
        } else {
            sender.forEach(
                { actor -> actor.tell(message + 1, self()) },
                { referee.tell(message, sender) },
//                { referee.tell(message, sender) },
            )
        }
    }

}

private val semaphore = Semaphore(1)
suspend fun main() {
    val referee = object : AbstractActor<Int>("Referee") {
        override fun onReceive(message: Int, sender: Result<Actor<Int>>) {
            println("Game ended after $message shots")
            semaphore.release()
        }
    }

    val player1 = Player("p1", "Ping", referee)
    val player2 = Player("p2", "Pong", referee)
    semaphore.acquire()
    player1.tell(1, Result(player2))
//    player1.tell(1, Result(null))
    semaphore.acquire()
}