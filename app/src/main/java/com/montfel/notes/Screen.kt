package com.montfel.notes

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object Notes : Screen

    @Serializable
    data class UpsertNote(
        val noteId: Int? = null
    ) : Screen
}