package attendance

import camp.nextstep.edu.missionutils.test.Assertions.assertNowTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class ApplicationTest2 : NsTest() {
    @Test
    fun `출석 확인 기능 테스트`() {
        assertNowTest(
            {
                runException("4")
                assertThat(output()).contains(
                        "- 빙티: 결석 3회, 지각 4회 (면담)",
                        "- 이든: 결석 2회, 지각 5회 (면담)",
                        "- 빙봉: 결석 1회, 지각 6회 (면담)",
                        "- 쿠키: 결석 2회, 지각 3회 (면담)",
                        "- 짱수: 결석 2회, 지각 0회 (경고)")
            },
            LocalDate.of(2024, 12, 14).atStartOfDay()
        )
    }
    override fun runMain() {
        main()
    }
}