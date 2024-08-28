package com.montfel.domain.repository

import com.montfel.domain.model.Note

interface FcmRepository {
    suspend fun sendNotification(token: String, note: Note)
}