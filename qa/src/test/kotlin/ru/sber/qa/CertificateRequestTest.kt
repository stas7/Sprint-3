package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class CertificateRequestTest {

    private val certificateType = mockk<CertificateType>()
    private val employeeNumber = 123L
    private val certificateRequest = CertificateRequest(employeeNumber, certificateType)

    @Test
    fun process() {
        val hrEmployeeNumber = 987L
        val scannedData = Random.nextBytes(100) // scanned data
        mockkConstructor(Certificate::class)
        mockkObject(Scanner)

        every { Scanner.getScanData() } returns scannedData
        val certificate = certificateRequest.process(hrEmployeeNumber)

        assertEquals(hrEmployeeNumber, certificate.processedBy)
        assertEquals(scannedData, certificate.data)
        assertEquals(certificateRequest, certificate.certificateRequest)
    }
}