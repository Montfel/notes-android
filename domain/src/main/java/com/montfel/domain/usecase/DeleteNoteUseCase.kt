package com.montfel.domain.usecase

import com.montfel.domain.model.Note

interface DeleteNoteUseCase {
    suspend operator fun invoke(note: Note)
}