package attendance.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun inputFunctionNumber(): String {
        return Console.readLine()
    }
    fun inputNickName() : String{
        println("닉네임을 입력해 주세요.")
        return Console.readLine()
    }
    fun inputSchoolTime() : String{
        println("등교 시간을 입력해 주세요.")
        return Console.readLine()
    }
}