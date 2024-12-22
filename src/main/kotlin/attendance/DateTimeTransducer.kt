package attendance

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeTransducer {
    fun monthAndDate(now: LocalDateTime): List<String> {
        val monthAndDateAndDateOfWeekFormat = now.format(DateTimeFormatter.ofPattern("M,d,E"))
        val monthAndDateAndDateOfWeek = monthAndDateAndDateOfWeekFormat.split(",")
        return monthAndDateAndDateOfWeek
    }

}