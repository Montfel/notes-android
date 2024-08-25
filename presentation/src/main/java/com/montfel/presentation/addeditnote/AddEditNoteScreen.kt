package com.montfel.presentation.addeditnote

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.presentation.R
import com.montfel.presentation.addeditnote.components.DatePickerDialogComponent
import com.montfel.presentation.addeditnote.components.TextFieldComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    noteId: Int? = null,
    onBack: () -> Unit
) {
    val viewModel: AddEditNoteViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var shouldOpenDateDialog by remember {
        mutableStateOf(false)
    }

    val text = stringResource(
        id = if (noteId != null) {
            R.string.edit_note
        } else {
            R.string.add_note
        }
    )

    LaunchedEffect(Unit) {
        noteId?.let {
            viewModel.onEvent(AddEditNoteEvent.GetNoteById(it))
        }

        viewModel.uiEvent.collect { event ->
            when (event) {
                AddEditNoteUiEvent.OnSaveNote -> onBack()
            }
        }
    }

    if (shouldOpenDateDialog) {
        DatePickerDialogComponent(
            onConfirm = {
                shouldOpenDateDialog = false
                viewModel.onEvent(AddEditNoteEvent.OnDueDateChange(it))
            },
            onCancel = { shouldOpenDateDialog = false },
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = text)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
        ) {
            TextFieldComponent(
                label = stringResource(id = R.string.title),
                placeholder = "",
                text = uiState.title,
                onValueChange = { viewModel.onEvent(AddEditNoteEvent.OnNoteTitleChange(it)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextFieldComponent(
                label = stringResource(id = R.string.description),
                placeholder = "",
                text = uiState.description,
                onValueChange = { viewModel.onEvent(AddEditNoteEvent.OnNoteDescriptionChange(it)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextFieldComponent(
                label = stringResource(id = R.string.due_date),
                placeholder = "",
                trailingIcon = {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                },
                enabled = false,
                text = uiState.dueDate,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        shouldOpenDateDialog = true
                    }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { viewModel.onEvent(AddEditNoteEvent.OnSaveNote) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = text)
            }
        }
    }
}