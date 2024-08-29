package com.montfel.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.domain.model.Note
import com.montfel.domain.usecase.DeleteNoteUseCase
import com.montfel.domain.usecase.GetAllNotesUseCase
import com.montfel.presentation.notification.NotesAlarmManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val notesAlarmManager: NotesAlarmManager
) : ViewModel() {

    private val _uiState = MutableLiveData(NotesUiState())
    val uiState: LiveData<NotesUiState> = _uiState

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
            getAllNotesUseCase().distinctUntilChanged().collect { notes ->
                _uiState.value = _uiState.value?.copy(notes = notes)
            }
        }
    }

    private fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase(note)
            notesAlarmManager.cancelAlarm(note)
        }
    }
}