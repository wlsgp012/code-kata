package dojo.book.joyofkotlin.ch12

import dojo.book.joyofkotlin.ch7.newresult.Result
import java.io.BufferedReader
import java.io.InputStreamReader

class ConsoleReader(reader: BufferedReader) : dojo.book.joyofkotlin.ch12.AbstractReader(reader) {
    override fun readString(msg: String): Result<Pair<String, Input>> {
        print("$msg")
        return readString()
    }

    override fun readInt(msg: String): Result<Pair<Int, Input>> {
        print("$msg")
        return readInt()
    }

    companion object {
        operator fun invoke(): ConsoleReader = ConsoleReader(BufferedReader(InputStreamReader(System.`in`)))
    }
}

fun main() {
    val input = ConsoleReader()
    val rString = input.readString("Enter your name:").map { t -> t.first }
    val nameMessage = rString.map { "Hello, $it" }
    nameMessage.forEach(::println, onFailure = { println(it.message) })
}