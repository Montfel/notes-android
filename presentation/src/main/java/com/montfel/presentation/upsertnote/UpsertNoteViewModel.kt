package com.montfel.presentation.upsertnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.domain.model.Note
import com.montfel.domain.usecase.GetNoteByIdUseCase
import com.montfel.domain.usecase.UpsertNoteUseCase
import com.montfel.presentation.notification.NotesAlarmManager
import com.montfel.presentation.util.DateFormat
import com.montfel.presentation.util.formatDate
import com.montfel.presentation.util.toLongWithTimeZero
import com.montfel.presentation.util.toUTC
import com.montfel.presentation.util.toUTCDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class UpsertNoteViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val upsertNoteUseCase: UpsertNoteUseCase,
    private val notesAlarmManager: NotesAlarmManager
) : ViewModel() {

    private val _uiState = MutableLiveData(UpsertNoteUiState())
    val uiState: LiveData<UpsertNoteUiState> = _uiState

    private val _uiEvent = Channel<UpsertNoteUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var dueTimestamp: Long = 0L
    private var currentNoteId: Long? = null

    fun onEvent(event: UpsertNoteEvent) {
        when (event) {
            is UpsertNoteEvent.OnUpsertNote -> onUpsertNote()
            is UpsertNoteEvent.OnDueDateChange -> onDueDateChange(event.dueTimestamp)
            is UpsertNoteEvent.OnNoteDescriptionChange -> onNoteDescriptionChange(event.description)
            is UpsertNoteEvent.OnNoteTitleChange -> onNoteTitleChange(event.title)
            is UpsertNoteEvent.GetNoteById -> getNoteById(event.noteId)
        }
    }

    private fun getNoteById(noteId: Long) {
        viewModelScope.launch {
            val note = getNoteByIdUseCase(noteId)
            currentNoteId = noteId
            dueTimestamp = note.dueDate

            _uiState.value = uiState.value?.copy(
                title = note.title,
                description = note.description,
                dueDate = note.dueDate.toUTCDate().formatDate(DateFormat.BRAZILIAN)
            )
        }
    }

    private fun onUpsertNote() {
        val titleSuccessful = uiState.value?.title?.isNotBlank() ?: false
        val dueDateSuccessful =
            uiState.value?.dueDate?.isNotBlank() ?: false && dueTimestamp >= Date().toLongWithTimeZero()

        if (titleSuccessful && dueDateSuccessful) {
            var note = Note(
                id = currentNoteId,
                title = uiState.value?.title.orEmpty(),
                description = uiState.value?.description.orEmpty(),
                dueDate = dueTimestamp
            )

            viewModelScope.launch {
                upsertNoteUseCase(note).also { noteId ->
                    if (noteId != -1L) {
                        note = note.copy(id = noteId)
                    }

                    notesAlarmManager.setUpAlarm(note)
                }

                _uiEvent.send(UpsertNoteUiEvent.OnSaveNote)
            }
        } else {
            _uiState.value = uiState.value?.copy(
                titleSuccessful = titleSuccessful,
                dueDateSuccessful = dueDateSuccessful
            )
        }
    }

    private fun onNoteTitleChange(title: String) {
        _uiState.value = uiState.value?.copy(
            title = title,
            titleSuccessful = title.isNotBlank()
        )
    }

    private fun onNoteDescriptionChange(description: String) {
        _uiState.value = uiState.value?.copy(description = description)
    }

    private fun onDueDateChange(dueTimestamp: Long) {
        this.dueTimestamp = dueTimestamp.toUTC()

        _uiState.value = uiState.value?.copy(
            dueDate = dueTimestamp.toUTCDate().formatDate(DateFormat.BRAZILIAN),
            dueDateSuccessful = dueTimestamp.toUTC() >= Date().toLongWithTimeZero()
        )
    }
}