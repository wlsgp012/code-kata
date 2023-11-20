package dojo.book.joyofkotlin.ch12

import dojo.book.joyofkotlin.ch5.List
import dojo.book.joyofkotlin.ch7.newresult.Result
import dojo.book.joyofkotlin.ch9.Stream

class ScriptReader : Input {

    private val commands: List<String>

    constructor(commands: List<String>) : super() {
        this.commands = commands
    }

    constructor(vararg commands: String) : super() {
        this.commands = List(*commands)
    }

    override fun readString(): Result<Pair<String, Input>> = when {
        commands.isEmpty() -> Result.failure("Not enough entries in script")
        else -> Result(commands.headSafe().getOrElse("") to ScriptReader(commands.drop(1)))
    }

    override fun readInt(): Result<Pair<Int, Input>> = when {
        commands.isEmpty() -> Result.failure("Not enough entries in script")
        Integer.parseInt(commands.headSafe().getOrElse("")) >= 0 -> {
            Result(Integer.parseInt(commands.headSafe().getOrElse("")) to ScriptReader(commands.drop(1)))
        }

        else -> Result()
    }

    override fun close() {
    }
}

fun readPersonsFromScript(vararg commands: String) =
    Stream.unfold(ScriptReader(*commands), ::person).toList()

fun main(args: Array<String>) {
    readPersonsFromScript("1", "a", "aa", "2", "b", "bb").forEach(::println)
}
