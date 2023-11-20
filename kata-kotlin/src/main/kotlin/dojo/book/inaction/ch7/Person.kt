package dojo.book.inaction.ch7

class Person(val firstName: String, val lastName: String): Comparable<Person> {
    override fun compareTo(other: Person): Int =
        compareValuesBy(this, other, Person::lastName, Person::firstName)
}

fun main() {
    val p1 = Person("Alice", "Smith")
    val p2 = Person("Bob", "Johnson")
    println(p1 < p2)
}
