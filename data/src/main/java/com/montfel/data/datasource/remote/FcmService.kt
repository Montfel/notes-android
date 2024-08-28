package com.montfel.data.datasource.remote

import com.montfel.data.model.dto.SendNotificationDto
import retrofit2.http.Body
import retrofit2.http.POST

interface FcmService {
    @POST("/send")
    suspend fun sendNotification(
        @Body body: SendNotificationDto
    )
}