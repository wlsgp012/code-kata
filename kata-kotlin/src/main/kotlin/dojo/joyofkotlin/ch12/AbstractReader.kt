package dojo.joyofkotlin.ch12

import dojo.joyofkotlin.ch7.newresult.Result
import java.io.BufferedReader

abstract class AbstractReader(private val reader: BufferedReader) : Input {
    override fun readString(): Result<Pair<String, Input>> = try {
        reader.readLine().let {
            when {
                it.isEmpty() -> Result()
                else -> Result(it to this)
            }
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun readInt(): Result<Pair<Int, Input>> = try {
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