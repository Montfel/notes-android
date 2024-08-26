package com.montfel.domain.usecase

import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import javax.inject.Inject

internal class DeleteNoteUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
): DeleteNoteUseCase {
    override suspend fun invoke(note: Note) {
        noteRepository.deleteNote(note = note)
    }
}