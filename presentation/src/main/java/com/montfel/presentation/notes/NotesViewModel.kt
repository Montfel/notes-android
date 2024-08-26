package com.montfel.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import com.montfel.presentation.notification.NotesAlarmManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val notesAlarmManager: NotesAlarmManager
): ViewModel() {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllNotes()
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> deleteNote(event.note)
        }
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            noteRepository.getAllNotes().distinctUntilChanged().collect { notes ->
                _uiState.update {
                    it.copy(notes = notes)
                }
            }
        }
    }

    private fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
            notesAlarmManager.cancelAlarm(note)
        }
    }
}