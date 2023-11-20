package dojo.book.inaction.ch4

class Secretive private constructor()

open class Cons {
    constructor(ctx: String)
    constructor(ctx: String, attr: Int)
}

class MyButton : Cons {
    constructor(ctx: String) : this(ctx, 1) {}

    //    constructor(ctx: String): super(ctx) {}
    constructor(ctx: String, attr: Int) : super(ctx, attr) {
        //
    }
}

class Person2(val name: String, val registered: Int = 1) {
    constructor(name: String) : this(name.toString(), 2) {
        println("sub")
    }

    init {
        println("main")
    }
}

fun main() {
    Person2("aaa") // main -> sub
}
