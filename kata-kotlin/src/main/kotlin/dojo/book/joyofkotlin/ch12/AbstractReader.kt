package dojo.book.joyofkotlin.ch12

import dojo.joyofkotlin.ch7.newresult.Result
import java.io.BufferedReader

abstract class AbstractReader(private val reader: BufferedReader) : dojo.book.joyofkotlin.ch12.Input {
    override fun readString(): Result<Pair<String, dojo.book.joyofkotlin.ch12.Input>> = try {
        reader.readLine().let {
            when {
                it.isEmpty() -> Result()
                else -> Result(it to this)
            }
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun readInt(): Result<Pair<Int, dojo.book.joyofkotlin.ch12.Input>> = try {
        reader.readLine().let {
            when {
                it.isEmpty() -> Result()
                else -> Result(it.toInt() to this)
            }
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun close() = reader.close()
}