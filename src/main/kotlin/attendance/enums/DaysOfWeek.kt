package attendance.enums

import kotlin.collections.find

enum class DaysOfWeek(private val daysOfWeek: String) {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    companion object {
        fun findDayOfWeek(input: String): String {
            return DaysOfWeek.entries.find { it.name == input }!!.daysOfWeek
        }
    }
}