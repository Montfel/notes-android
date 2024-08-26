package com.montfel.domain.usecase

import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import javax.inject.Inject

internal class GetNoteByIdUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
): GetNoteByIdUseCase {
    override suspend fun invoke(noteId: Int): Note {
        return noteRepository.getNoteById(noteId = noteId)
    }
}