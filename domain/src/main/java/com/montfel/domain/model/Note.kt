package com.montfel.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Long? = null,
    val title: String,
    val description: String,
    val dueDate: Long
)
