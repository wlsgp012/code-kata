package dojo.etc.dsl

import dojo.etc.dsl.DateUtil.Tense.ago
import dojo.etc.dsl.DateUtil.Tense.from_now
import java.util.Calendar

infix fun Int.days(timing: DateUtil.Tense) = DateUtil(this, timing)

class DateUtil(val number: Int, val tense: Tense) {
    enum class Tense {
        ago, from_now
    }

    override fun toString(): String {
        val today = Calendar.getInstance()
        when (tense) {
            ago -> today.add(Calendar.DAY_OF_MONTH, -number)
            from_now -> today.add(Calendar.DAY_OF_MONTH, number)
        }
        return today.time.toString()
    }
}

fun main() {
    println(2 days ago)
    println(3 days from_now)
}
