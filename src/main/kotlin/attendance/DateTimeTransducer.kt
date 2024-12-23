package attendance

import attendance.enums.DaysOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeTransducer {
    fun nowMonthAndDate(now: LocalDateTime): List<String> {
        val monthAndDateAndDateOfWeekFormat = now.format(DateTimeFormatter.ofPattern("M,d,E"))
        val monthAndDateAndDateOfWeek = monthAndDateAndDateOfWeekFormat.split(",")
        return monthAndDateAndDateOfWeek
    }
    fun findDateInformation(modifyDate: String): String {
        val dayOfWeekValue = LocalDate.of(2024,12,modifyDate.toInt()).dayOfWeek
        return DaysOfWeek.findDayOfWeek(dayOfWeekValue.toString())

    }

}