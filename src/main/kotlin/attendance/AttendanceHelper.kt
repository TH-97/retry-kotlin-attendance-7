package attendance

import attendance.model.Attendances

class AttendanceHelper {
    fun checkAttendance(nowDayOfWeek: String, time: String): String {
        val hourAndMinute = time.split(":")
        if (hourAndMinute[0] == "--") return AttendanceState.ABSENCE.getState()
        if (nowDayOfWeek == "월") {
            if (hourAndMinute[0].toInt() in 7..12 && hourAndMinute[1].toInt() in 0 .. 59)
                return AttendanceState.ATTENDANCE.getState()
            if (hourAndMinute[0].toInt() == 13 && hourAndMinute[1].toInt() in 0 .. 5)
                return AttendanceState.ATTENDANCE.getState()
            if (hourAndMinute[0].toInt() == 13 && hourAndMinute[1].toInt() in 6 .. 30)
                return AttendanceState.TARDINESS.getState()
        }
        else{
            if (hourAndMinute[0].toInt() in 7..9 && hourAndMinute[1].toInt() in 0 .. 59)
                return AttendanceState.ATTENDANCE.getState()
            if (hourAndMinute[0].toInt() == 10 && hourAndMinute[1].toInt() in 0 .. 5)
                return AttendanceState.ATTENDANCE.getState()
            if (hourAndMinute[0].toInt() == 10 && hourAndMinute[1].toInt() in 6 .. 30)
                return AttendanceState.TARDINESS.getState()
        }
        return AttendanceState.ABSENCE.getState()
    }
    fun modifyAttendance(
        modifyNickName: String,
        nowMonth: String,
        modifyDay: String,
        modifySchoolTime: String,
        fileList: MutableList<Attendances>
    ) : Pair<String,String>{
        val findAttendance = fileList.find {
            it.datetime.split(" ")[0].split("-")[2] == modifyDay && it.nickname == modifyNickName
        }
        if (findAttendance != null){
            val findIndexOfFileList = fileList.indexOf(findAttendance)
            val before = fileList[findIndexOfFileList].datetime.split(" ")[1]
            val after =  modifySchoolTime
            fileList[findIndexOfFileList] =
                Attendances(modifyNickName,"2024-${nowMonth}-${modifyDay} ${modifySchoolTime}")
            return Pair(before,after)

        }else{
            val before = "--:--"
            val after =  modifySchoolTime
            fileList.add(
                Attendances(modifyNickName,"2024-${nowMonth}-${modifyDay} ${modifySchoolTime}")
            )
            return Pair(before,after)
        }
    }
}