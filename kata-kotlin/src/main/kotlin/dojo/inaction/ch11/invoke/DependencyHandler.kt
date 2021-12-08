package dojo.inaction.ch11.invoke

class DependencyHandler {
    fun compile(coordinate: String) {
        println("Added dependency on $coordinate")
    }

    operator fun invoke(body: DependencyHandler.() -> Unit) {
        body()
    }
}

fun main() {
    val dependencies = DependencyHandler()
    dependencies.compile("org.test.xxx")
    dependencies {
        compile("org.test.yyy")
    }
}