package attendance

import attendance.enums.Error
import attendance.model.Attendances
import java.time.LocalDate

class Validator {
    fun checkDayOfWeek(nowMonth: String, nowDay: String, nowDayOfWeek: String) {
        require(nowDayOfWeek != "일")
        {"[ERROR] ${nowMonth}월 ${nowDay}일 ${nowDayOfWeek}요일은 등교일이 아닙니다."}
        require(nowDayOfWeek != "토")
        {"[ERROR] ${nowMonth}월 ${nowDay}일 ${nowDayOfWeek}요일은 등교일이 아닙니다."}
    }
    fun findNickName(nickName: String, fileList: MutableList<Attendances>) {
        val findNickName = fileList.find { it.nickname == nickName }
        require(findNickName != null){ Error.UNREGISTERED_NICKNAME.getMessage() }
    }
    fun findAlreadyAttendance(
        nickName: String,
        fileList: MutableList<Attendances>,
        nowMonth: String,
        nowDay: String
    ) {
        val filterNickname = fileList.filter { it.nickname == nickName }
        filterNickname.forEach {
            println(it.datetime)
            val splitDateTime = it.datetime.split(" ")
            val monthAndDate = splitDateTime[0].split("-")
            if(monthAndDate[1] == nowMonth)
                require(monthAndDate[2] != nowDay) { Error.AGAIN_ATTENDANCE.getMessage() }
        }
    }


    fun validateCampusOperationHours(time: String) {
        val hourAndMinute = time.split(":")
        require(hourAndMinute[0].all { it.isDigit() }){ Error.FORM.getMessage() }
        require(hourAndMinute[0].toInt() in 8 .. 22){ Error.FORM.getMessage() }
        require(hourAndMinute[1].toInt() in 0 .. 59){ Error.FORM.getMessage() }
        if (hourAndMinute[0].toInt() == 23) require(hourAndMinute[1].toInt() == 0)
            { Error.NOT_CAMPUS_HOURS.getMessage()}
    }
    fun validateModifyDate(modifyDate: String, nowDay: String) {
        require(nowDay.toInt() >= modifyDate.toInt()){ Error.ATTENDANCE_FUTURE_DATE.getMessage() }
        val dayOfWeek = LocalDate.of(2024,12,modifyDate.toInt()).dayOfWeek.value
        require(dayOfWeek.toInt() in 1 .. 5) {"[Error] 주말은 출석이 되어 있지 않습니다"}

    }
}