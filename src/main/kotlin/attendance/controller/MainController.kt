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
            "3" -> {three(fileList)}
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
        outputChangeAttendance(nowMonth,nowDay,dayOfWeek,beforeSchoolTime,afterSchoolTime,beforeSchoolTimeState,afterSchoolTimeState)
        run(fileList)
    }
    fun three(fileList: MutableList<Attendances>) {
        val nickName = inputView.inputNickName()
        val filterFileList = fileList.filter { it.nickname == nickName }
        val sortedFileList = filterFileList.sortedBy { it.datetime.split(" ")[0].split("-")[2] }
    }
}