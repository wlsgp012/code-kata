package dojo.delegation

import kotlin.properties.Delegates.observable
import kotlin.properties.Delegates.vetoable

fun getTemperature(city: String): Double {
    println("fetch from webservice for $city")
    return 3.0
}

fun main() {
    val temerature by lazy { getTemperature("seoul") }

    // observale
    var count by observable(0) { property, oldValue, newValue -> println("Property: $property old: $oldValue new: $newValue") }
    println("count is $count")
    count++
    println("count is $count")
    count--
    println("count is $count")

    // vetoable
    var c by vetoable(0) { property, oldValue, newValue -> newValue > oldValue }
    println("c is $c")
    c++
    println("c is $c")
    c--
    println("c is $c")
}
