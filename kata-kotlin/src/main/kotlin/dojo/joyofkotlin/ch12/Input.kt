package dojo.joyofkotlin.ch12

import dojo.joyofkotlin.ch7.newresult.Result
import java.io.Closeable

interface Input : Closeable {
    fun readString(): Result<Pair<String, Input>>
    fun readInt(): Result<Pair<Int, Input>>
    fun readString(msg: String): Result<Pair<String, Input>> = readString()
    fun readInt(msg: String): Result<Pair<Int, Input>> = readInt()
}