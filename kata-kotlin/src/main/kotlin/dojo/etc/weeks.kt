package dojo.etc

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*

fun printWeek() {
    val now = LocalDate.now()
    val firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    val startOfCurrentWeek: LocalDate = now.with(TemporalAdjusters.previousOrSame(firstDayOfWeek))
    val lastDayOfWeek: DayOfWeek = firstDayOfWeek.plus(6) // or minus(1)
    val endOfWeek: LocalDate = now.with(TemporalAdjusters.nextOrSame(lastDayOfWeek))
    println("$startOfCurrentWeek ~ $endOfWeek")
}

fun main() {
    printWeek()
}