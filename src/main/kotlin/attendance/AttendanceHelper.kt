package attendance

class AttendanceHelper {
    fun checkAttendance(nowMonth: String, nowDay: String, nowDayOfWeek: String, time: String): String {
        val hourAndMinute = time.split(":")
        if (nowDayOfWeek == "월") {
            if (hourAndMinute[0].toInt() in 7..12 && hourAndMinute[1].toInt() in 0 .. 59)
                return AttendanceState.ATTENDANCE.getState()
            if (hourAndMinute[0].toInt() == 13 && hourAndMinute[1].toInt() in 0 .. 5)
                return AttendanceState.ATTENDANCE.getState()
            if (hourAndMinute[0].toInt() == 13 && hourAndMinute[1].toInt() in 6 .. 30)
                return AttendanceState.TARDINESS.getState()
        }
        else{
            if (hourAndMinute[0].toInt() in 7..12 && hourAndMinute[1].toInt() in 0 .. 59)
                return AttendanceState.ATTENDANCE.getState()
            if (hourAndMinute[0].toInt() == 13 && hourAndMinute[1].toInt() in 0 .. 5)
                return AttendanceState.ATTENDANCE.getState()
            if (hourAndMinute[0].toInt() == 13 && hourAndMinute[1].toInt() in 6 .. 30)
                return AttendanceState.TARDINESS.getState()
        }
        return AttendanceState.ABSENCE.getState()
    }
}