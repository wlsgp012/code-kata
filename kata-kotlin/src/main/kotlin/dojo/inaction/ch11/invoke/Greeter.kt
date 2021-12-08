package dojo.inaction.ch11.invoke

class Greeter(val greeting: String) {
    operator fun invoke(name: String){
        println("$greeting, $name")
    }
}

fun main() {
    val bavarianGreeter = Greeter("Servus")
    bavarianGreeter("Dmittry")
    Greeter("111")("2222")
}