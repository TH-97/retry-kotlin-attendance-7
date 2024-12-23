package attendance.view

class OutputView {
    fun introduceFunction(month: String, date: String, dayOfWeek: String) {
        println("오늘은 ${month}월 ${date}일 ${dayOfWeek}요일입니다. 기능을 선택해 주세요.")
        println("1. 출석 확인\n" +
                "2. 출석 수정\n" +
                "3. 크루별 출석 기록 확인\n" +
                "4. 제적 위험자 확인\n" +
                "Q. 종료")
    }
    fun outputState(
        nowMonth: String,
        nowDay: String,
        nowDayOfWeek: String,
        time: String,
        attendanceState: String
    ) {
        println("${nowMonth}월 ${nowDay}일 ${nowDayOfWeek}요일 ${time} ${attendanceState}")
    }
    fun outputChangeAttendance(
        nowMonth: String,
        modifyDay: String,
        dayOfWeek: String,
        beforeSchoolTime: String,
        afterSchoolTime: String,
        beforeSchoolTimeState: String,
        afterSchoolTimeState: String
    ) {
        println("${nowMonth}월 ${modifyDay}일 ${dayOfWeek}요일 " +
                "${beforeSchoolTime} ${beforeSchoolTimeState} " +
                "-> ${afterSchoolTime} ${afterSchoolTimeState} 수정 완료!")
    }
    fun outputAttendanceState(attendance: Int, tardiness: Int, absence: Int) {
        println("출석: ${attendance}회\n" +
                "지각: ${tardiness}회\n" +
                "결석: ${absence}회\n")
        val condition = absence + (tardiness/3)
        if (condition == 2) println("경고 대상자입니다.")
        if (condition in 3 .. 5) println("면담 대상자입니다.")
        if (condition > 5) println("제적 대상자입니다.")
    }
    fun outputEliminationTarget(nickName: String, tardiness: Int, absence: Int) {
        val condition = absence + (tardiness/3)
//        println(nickName + condition)
        if (condition == 2) println("- ${nickName}: 결석 ${absence}회, 지각 ${tardiness}회 (경고)")
        if (condition in 3 .. 5) println("- ${nickName}: 결석 ${absence}회, 지각 ${tardiness}회 (면담)")
    }
}
