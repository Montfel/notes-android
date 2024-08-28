package com.montfel.data.repository

import com.montfel.data.datasource.remote.FcmService
import com.montfel.data.model.dto.SendNotificationDto
import com.montfel.domain.model.Note
import com.montfel.domain.repository.FcmRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FcmRepositoryImplTest {
    @MockK
    private lateinit var service: FcmService
    private lateinit var repository: FcmRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        repository = FcmRepositoryImpl(service)
    }

    @Test
    fun `sendNotification should call service with correct SendNotificationDto when invoked with token and Note`() {
        runTest {
            val token = "token"
            val note = Note(
                id = null,
                title = "vituperata",
                description = "quas",
                dueDate = 1318
            )
            val sendNotificationDto = SendNotificationDto(
                token = token,
                title = note.title,
                description = note.description
            )

            coEvery { service.sendNotification(sendNotificationDto = sendNotificationDto) } just runs
            repository.sendNotification(token = token, note = note)

            coVerify { service.sendNotification(sendNotificationDto = sendNotificationDto) }
        }
    }
}