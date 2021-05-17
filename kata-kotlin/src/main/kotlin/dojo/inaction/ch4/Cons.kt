package dojo.inaction.ch4

class Secretive private constructor() {}

open class Cons {
    constructor(ctx: String)
    constructor(ctx: String, attr: Int)
}

class MyButton: Cons {
    constructor(ctx: String): this(ctx, 1) {}
//    constructor(ctx: String): super(ctx) {}
    constructor(ctx: String, attr: Int): super(ctx, attr) {
        //
    }
}