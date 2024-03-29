package dojo.book.inaction.ch4

class DelegatingCollection<T>(innerList: Collection<T> = ArrayList()): Collection<T> by innerList {}

class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet()): MutableCollection<T> by innerSet {
    var objectsAdded = 0

    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

fun main() {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1,1,3))
    println("${cset.objectsAdded} objects were added, ${cset.size} remain")
}