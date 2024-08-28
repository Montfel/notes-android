package com.montfel.data.repository

import com.montfel.data.datasource.remote.FcmService
import com.montfel.data.model.dto.SendNotificationDto
import com.montfel.domain.model.Note
import com.montfel.domain.repository.FcmRepository
import javax.inject.Inject

internal class FcmRepositoryImpl @Inject constructor(
    private val service: FcmService
) : FcmRepository {
    override suspend fun sendNotification(token: String, note: Note) {
        service.sendNotification(
            SendNotificationDto(
                token = token,
                title = note.title,
                description = note.description
            )
        )
    }
}