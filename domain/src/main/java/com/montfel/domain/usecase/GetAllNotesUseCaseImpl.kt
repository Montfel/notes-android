package com.montfel.domain.usecase

import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetAllNotesUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
): GetAllNotesUseCase {
    override fun invoke(): Flow<List<Note>> {
        return noteRepository.getAllNotes()
    }
}