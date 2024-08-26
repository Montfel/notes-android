package com.montfel.notes

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object Notes : Screen

    @Serializable
    data class AddEditNote(
        val noteId: Int? = null
    ) : Screen
}