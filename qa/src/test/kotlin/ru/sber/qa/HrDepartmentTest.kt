package src.main.kotlin.ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.sber.qa.*
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

internal class HrDepartmentTest {
    private val certificateRequest = mockk<CertificateRequest>()
    private val timeZone = ZoneOffset.UTC

    @BeforeEach
    fun setUp() {
        val clockFixed = Clock.fixed(Instant.parse("2021-09-16T10:00:00Z"), ZoneOffset.UTC)
        HrDepartment.clock = clockFixed
    }

    @Test
    fun receiveRequest() {
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK
        assertDoesNotThrow({ HrDepartment.receiveRequest(certificateRequest) })
    }

    @Test
    fun processNextRequest() {
        val certificate = mockk<Certificate>()
        val hrEmployeeNumber = 1000L

        every { certificateRequest.process(hrEmployeeNumber) } returns certificate
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        unmockkAll()
    }

}