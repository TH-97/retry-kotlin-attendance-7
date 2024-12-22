package attendance

import attendance.controller.MainController
import attendance.view.InputView
import attendance.view.OutputView

fun main() {
    val outputView = OutputView()
    val inputView = InputView()
    val dateTimeTransducer = DateTimeTransducer()
    val fileManager = FileManager()
    val validator = Validator()
    val attendanceHelper = AttendanceHelper()
    MainController(outputView,inputView,dateTimeTransducer,fileManager,validator,attendanceHelper).importFile()
}
