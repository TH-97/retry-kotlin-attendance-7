package attendance.controller

import attendance.AttendanceHelper
import attendance.AttendanceState
import attendance.DateTimeTransducer
import attendance.Error
import attendance.FileManager
import attendance.Validator
import attendance.model.Attendances
import attendance.view.InputView
import attendance.view.OutputView
import camp.nextstep.edu.missionutils.DateTimes

class MainController(
    private val outputView: OutputView,
    private val inputView: InputView,
    private val dateTimeTransducer: DateTimeTransducer,
    private val fileManager: FileManager,
    private val validator: Validator,
    private val attendanceHelper: AttendanceHelper
) {
    fun importFile(){
        val fileList = mutableListOf<Attendances>()
        fileManager.importFile(fileList)
        run(fileList)
    }
    fun run(fileList : MutableList<Attendances>){
        val nowDate = dateTimeTransducer.nowMonthAndDate(DateTimes.now())
        val nowMonth = nowDate[0]
        val nowDay = nowDate[1]
        val nowDayOfWeek = nowDate[2]
        outputView.introduceFunction(nowMonth,nowDay,nowDayOfWeek)
        when(inputView.inputFunctionNumber()){
            "1" -> {
                validator.checkDayOfWeek(nowMonth,nowDay,nowDayOfWeek)
                one(fileList,nowMonth,nowDay,nowDayOfWeek)
            }
            "2" -> {two(fileList,nowDay,nowMonth)}
            "3" -> {three(fileList,nowDay)}
            "4" -> {}
            "Q" -> {}
            else -> throw IllegalArgumentException(Error.FORM.getMessage())
        }
    }
    fun one(
        fileList: MutableList<Attendances>,
        nowMonth: String,
        nowDay: String,
        nowDayOfWeek: String
    ) {
        val nickName = inputView.inputNickName()
        validator.findNickName(nickName,fileList)
        validator.findAlreadyAttendance(nickName,fileList,nowMonth,nowDay)
        val time = inputView.inputSchoolTime()
        validator.validateCampusOperationHours(time)
        val attendanceState = attendanceHelper.checkAttendance(nowDayOfWeek,time)
        outputView.outputState(nowMonth,nowDay,nowDayOfWeek,time,attendanceState)
        if (attendanceState != AttendanceState.ABSENCE.getState())
            fileList.addFirst(Attendances(nickName,"2024-${nowMonth}-${nowDay} ${time}"))
        run(fileList)
    }
    fun two(fileList: MutableList<Attendances>, nowDay: String, nowMonth: String) {
        val modifyNickName = inputView.inputModifyNickName()
        validator.findNickName(modifyNickName,fileList)
        val modifyDate = inputView.inputModifyDate()
        validator.validateModifyDate(modifyDate,nowDay)
        val modifySchoolTime = inputView.inputModifySchoolTime()
        validator.validateCampusOperationHours(modifySchoolTime)
        val dayOfWeek = dateTimeTransducer.findDateInformation(modifyDate)
        val (beforeSchoolTime,afterSchoolTime) =
            attendanceHelper.modifyAttendance(modifyNickName,nowMonth,modifyDate,modifySchoolTime,fileList)
        val beforeSchoolTimeState = attendanceHelper.checkAttendance(dayOfWeek,beforeSchoolTime)
        val afterSchoolTimeState = attendanceHelper.checkAttendance(dayOfWeek,afterSchoolTime)
        outputView.
        outputChangeAttendance(nowMonth,modifyDate,dayOfWeek,beforeSchoolTime,afterSchoolTime,beforeSchoolTimeState,afterSchoolTimeState)
        run(fileList)
    }
    fun three(fileList: MutableList<Attendances>, nowDay: String) {
        val nickName = inputView.inputNickName()
        val filterFileList = fileList.filter { it.nickname == nickName }
        val sortedFileList = filterFileList.sortedBy { it.datetime.split(" ")[0].split("-")[2] }
        var attendance = 0
        var tardiness = 0
        var absence = 0
        for (i in 1..nowDay.toInt()-1){
            val value = sortedFileList.find { it.datetime.split(" ")[0].split("-")[2].toInt() == i }
            val dayOfWeek = dateTimeTransducer.findDateInformation(i.toString())
            var time = "--:--"
            var month = "12"
            var day = i.toString()
            if (day.length == 1) day = "0$i"
            if (value != null) {
                time = value.datetime.split(" ")[1]
                month = value.datetime.split(" ")[0].split("-")[1]
                day = value.datetime.split(" ")[0].split("-")[2]
                val state = attendanceHelper.checkAttendance(dayOfWeek, time)
                when (state.replace("(", "").replace(")", "")) {
                    "출석" -> {
                        attendance++; outputView.outputState(month, day, dayOfWeek, time, state)
                    }

                    "지각" -> {
                        tardiness++; outputView.outputState(month, day, dayOfWeek, time, state)
                    }

                    "결석" -> {
                        absence++;outputView.outputState(month, day, dayOfWeek, time, state)
                    }
                }
            }else if (dayOfWeek != "일" && dayOfWeek != "토"){
                absence++; outputView.outputState(month, day, dayOfWeek, time, "(결석)")
            }
        }
        outputView.outputAttendanceState(attendance,tardiness,absence)
    }
}