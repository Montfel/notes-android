package com.montfel.notes

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data class AddEditNote(
        val noteId: Int? = null
    ) : Screen
}