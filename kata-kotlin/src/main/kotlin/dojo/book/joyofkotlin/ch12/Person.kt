package dojo.book.joyofkotlin.ch12

import dojo.book.joyofkotlin.ch5.List
import dojo.book.joyofkotlin.ch7.newresult.Result
import dojo.book.joyofkotlin.ch9.Stream

data class Person(val id: Int, val firstName: String, val lastName: String)

/**
 * p.489 12-2
 */
fun person(input: Input): Result<Pair<Person, Input>> =
    input.readInt("Enter ID:").flatMap { id ->
        id.second.readString("Enter first name:").flatMap { firstName ->
            firstName.second.readString("Enter last name:").map {lastName ->
                Person(id.first, firstName.first, lastName.first) to lastName.second
            }
        }
    }

fun readPersonsFromConsole(): List<Person> = Stream.unfold(ConsoleReader(), ::person).toList()

fun main() {
    readPersonsFromConsole().forEach(::println)
}