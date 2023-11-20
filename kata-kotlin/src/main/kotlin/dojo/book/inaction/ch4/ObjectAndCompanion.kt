package dojo.book.inaction.ch4

data class Person(val name: String) {
    object NameComparator: Comparator<Person>{
        override fun compare(o1: Person, o2: Person): Int = o1.name.compareTo(o2.name)
    }
}

class A {
    companion object {
        fun bar(){
            println("Companion object called")
        }
    }
}

class UserO private constructor(val nickname: String){
    companion object{
        fun newSubscribingUser(email: String) = UserO(email.substringBefore('@'))
    }
}

fun main() {
    val persons = listOf(Person("Bob"), Person("Alice"))
    println(persons.sortedWith(Person.NameComparator))
    A.bar()
}