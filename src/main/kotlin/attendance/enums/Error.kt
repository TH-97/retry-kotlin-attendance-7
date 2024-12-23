package attendance.enums

enum class Error(private val message : String) {
    FORM("[ERROR] 잘못된 형식을 입력하였습니다."),
    UNREGISTERED_NICKNAME("[ERROR] 등록되지 않은 닉네임입니다."),
    ATTENDANCE_FUTURE_DATE("[ERROR] 아직 수정할 수 없습니다."),
    NOT_CAMPUS_HOURS("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다."),
    AGAIN_ATTENDANCE("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");

    fun getMessage(): String {
        return message
    }
}