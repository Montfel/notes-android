package com.montfel.domain.usecase

import com.montfel.domain.model.Note

interface AddNoteUseCase {
    suspend operator fun invoke(note: Note)
}