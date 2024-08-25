package com.montfel.presentation.addeditnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import com.montfel.presentation.util.formatDate
import com.montfel.presentation.util.toUTCDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddEditNoteUiState())
    val uiState = _uiState.asStateFlow()

    private var dueTimestamp: Long = 0L
    private var currentNoteId: Int? = null

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.OnAddEditNote -> onAddNote()
            is AddEditNoteEvent.OnDueDateChange -> onDueDateChange(event.dueDate)
            is AddEditNoteEvent.OnEditNoteDescriptionChange -> onNoteDescriptionChange(event.description)
            is AddEditNoteEvent.OnEditNoteTitleChange -> onNoteTitleChange(event.title)
            is AddEditNoteEvent.GetEditNoteById -> getNoteById(event.noteId)
        }
    }

    private fun getNoteById(noteId: Int) {
        viewModelScope.launch {
            currentNoteId = noteId
            val note = noteRepository.getNoteById(noteId)

            _uiState.update {
                it.copy(
                    title = note.title,
                    description = note.description,
                    dueDate = note.dueDate.toUTCDate().formatDate()
                )
            }
        }
    }

    private fun onAddNote() {
        viewModelScope.launch {
            noteRepository.addNote(
                Note(
                    id = currentNoteId,
                    title = uiState.value.title,
                    description = uiState.value.description,
                    dueDate = dueTimestamp
                )
            )
        }
    }

    private fun onNoteTitleChange(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    private fun onNoteDescriptionChange(description: String) {
        _uiState.update {
            it.copy(description = description)
        }
    }

    private fun onDueDateChange(dueTimestamp: Long) {
        this.dueTimestamp = dueTimestamp

        _uiState.update {
            it.copy(dueDate = dueTimestamp.toUTCDate().formatDate())
        }
    }
}