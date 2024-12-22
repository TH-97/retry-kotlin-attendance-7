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
}
