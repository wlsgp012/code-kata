interface Worker {
    fun work()
    fun takeVacation()
    fun fileTimeSheet() = println("Why? Really?")
}

class JavaProgrammer : Worker {
    override fun work() {
        println("write Java")
    }

    override fun takeVacation() {
        println("code at the beach")
    }
}

class CSharpProgrammer : Worker {
    override fun work() {
        println("write C#")
    }

    override fun takeVacation() {
        println("branch at th ranch")
    }
}

/**
 * 가장 오른쪽에 있는 델리게이션은 속성이 아니라 파라미터이다. 선언에서 실제로 staff란 이름의 파라미터를 받은 후 staff란 이름의 멤버에 할당한다.
 * 주어진 객체에는 두개의 참조가 있다. 하나는 클래스 안에 백킹 필드로서 존재하는 참조, 또 하나는 델리게이션의 목적으로 존재하는 참조다.
 */
class Manager(var staff: Worker) : Worker by staff

fun main() {
    val doe = Manager(JavaProgrammer())
    println(doe.staff.javaClass.simpleName)
    doe.work()
    println("changing staff")
    /**
     * 속성을 변경했지만 필드만 변경한 것이지 델리게이션의 참조를 변경한 것은 아니다.
     * 델리게이션이 JavaProgrammer의 인스턴스를 사용중이기 때문에 gc의 대상이 되지 않는다
     */
    doe.staff = CSharpProgrammer()
    println(doe.staff.javaClass.simpleName)
    doe.work()
}
