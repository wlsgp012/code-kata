package dojo.inaction.ch11.lambda

open class Tag(val name: String){
    private val children = mutableListOf<Tag>()
    protected fun <T: Tag> doInit(child: T, init: T.() -> Unit){
        child.init()
        children.add(child)
    }

}

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

class TABLE: Tag("table") {
    fun tr(init: TR.() -> Unit) = doInit(TR(), init)
}
class TR: Tag("tr"){
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}

class TD: Tag("td")

fun createTable() = table { tr { td{ } } }
fun createTable2() = table { (this@table).tr { (this@tr).td{ } } } //p.155
