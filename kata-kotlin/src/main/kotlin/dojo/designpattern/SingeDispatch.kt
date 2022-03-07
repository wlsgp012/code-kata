package dojo.designpattern

interface Format
class PDF : Format
class XML : Format

interface Item {
    fun export(pdf: PDF)
    fun export(xml: XML)
}

class Message : Item {
    override fun export(f: PDF) {
        PDFExporter.export(this)
    }

    override fun export(xml: XML) {
        XMLExporter.export(this)
    }
}

class Activity : Item {
    override fun export(pdf: PDF) {
        PDFExporter.export(this)
    }

    override fun export(xml: XML) {
        XMLExporter.export(this)
    }
}

object PDFExporter {
    fun export(item: Item) {
        println("Pdf Exporter : " + item.javaClass.getSimpleName())
    }
}

object XMLExporter {
    fun export(item: Item) {
        println("Xml Exporter : " + item.javaClass.getSimpleName())
    }
}

object Main {
    fun main() {
        val i: Item = Activity()
        val f: Format = PDF()
        /*
        코틀린 역시 더블디스패치를 언어 차원에서 사용할 수 없다.
        메소드 오버로딩은 정적 디스패치(static dispatch)를 한다.
        런타임에 타입을 동적으로 체크하는 것이 아니라 미리 컴파일 이전에 타입을 알 수 있어야 됨.
        하지만 그렇지 않기 때문에 상위타입(인터페이스)을 하위타입의 파라미터를 가진 메서드에 호출할 수 없다.
         */
//        i.export(f)
    }
}