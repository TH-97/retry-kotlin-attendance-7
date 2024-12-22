package attendance

enum class AttendanceState(private val state : String) {
    ATTENDANCE("(출석)"),
    TARDINESS("(지각)"),
    ABSENCE("(결석)");

    fun getState(): String {
        return this.state
    }
}