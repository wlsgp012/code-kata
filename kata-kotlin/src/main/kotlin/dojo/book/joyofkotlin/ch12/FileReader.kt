package dojo.book.joyofkotlin.ch12

import dojo.book.joyofkotlin.ch5.List
import dojo.book.joyofkotlin.ch7.newresult.Result
import dojo.book.joyofkotlin.ch9.Stream
import java.io.BufferedReader
import java.io.File

class FileReader private constructor(private val reader: BufferedReader) : dojo.book.joyofkotlin.ch12.AbstractReader(reader), AutoCloseable {
    override fun close() {
        reader.close()
    }

    companion object {
        operator fun invoke(path: String): Result<Input> = try {
            Result(FileReader(File(path).bufferedReader()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

/**
 * p.492 12-3
 */
fun readPersonsFromFile(path: String): Result<List<Person>> =
    FileReader(path).map { input ->
        input.use {
            Stream.unfold(it, ::person).toList()
        }
    }

fun main() {
    val path = FileReader::class.java.classLoader.getResource("joyofkotlin/ch12/data.txt").path
    readPersonsFromFile(path).forEach({
        println(it)
    })
}