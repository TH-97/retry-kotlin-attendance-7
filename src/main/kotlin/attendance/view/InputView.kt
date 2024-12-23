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
    fun inputModifyNickName(): String {
        println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.")
        return Console.readLine()
    }
    fun inputModifyDate() :String{
        println("수정하려는 날짜(일)를 입력해 주세요.")
        val input = Console.readLine()
        if (input.length == 1) return "0$input"
        return input
    }
    fun inputModifySchoolTime() : String{
        println("언제로 변경하겠습니까?")
        return Console.readLine()
    }

}