package com.montfel.data.model.dto

data class SendNotificationDto(
    val token: String,
    val title: String,
    val description: String,
)
