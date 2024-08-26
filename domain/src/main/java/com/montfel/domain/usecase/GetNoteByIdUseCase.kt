package com.montfel.domain.usecase

import com.montfel.domain.model.Note

interface GetNoteByIdUseCase {
    suspend operator fun invoke(noteId: Int): Note
}