package dojo.joyofkotlin.ch12

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * p.500 12-5
 */
object Console {
    fun readln(): IO<String> = IO {
        val reader = BufferedReader(InputStreamReader(System.`in`))
        reader.readLine()
    }

    fun println(o: Any): IO<Unit> = IO { kotlin.io.println(o) }
    fun print(o: Any): IO<Unit> = IO { kotlin.io.print(o) }
}