package com.montfel.presentation.upsertnote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.presentation.R
import com.montfel.presentation.theme.NotesTheme
import com.montfel.presentation.upsertnote.components.DatePickerDialogComponent
import com.montfel.presentation.upsertnote.components.TextFieldComponent
import com.montfel.presentation.util.TestTags

@Composable
fun UpsertNoteRoute(
    noteId: Long? = null,
    onBack: () -> Unit
) {
    val viewModel: UpsertNoteViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        noteId?.let {
            viewModel.onEvent(UpsertNoteEvent.GetNoteById(it))
        }

        viewModel.uiEvent.collect { event ->
            when (event) {
                UpsertNoteUiEvent.OnSaveNote -> onBack()
            }
        }
    }

    UpsertNoteScreen(
        noteId = noteId,
        onBack = onBack,
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpsertNoteScreen(
    noteId: Long?,
    onBack: () -> Unit,
    uiState: UpsertNoteUiState,
    onEvent: (UpsertNoteEvent) -> Unit
) {
    val text = stringResource(
        id = if (noteId != null) {
            R.string.edit_note
        } else {
            R.string.add_note
        }
    )

    var shouldOpenDateDialog by remember {
        mutableStateOf(false)
    }

    if (shouldOpenDateDialog) {
        DatePickerDialogComponent(
            onConfirm = {
                shouldOpenDateDialog = false
                onEvent(UpsertNoteEvent.OnDueDateChange(it))
            },
            onCancel = { shouldOpenDateDialog = false },
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            TextFieldComponent(
                label = stringResource(id = R.string.title),
                text = uiState.title,
                errorMessage = stringResource(id = R.string.title_error_message),
                hasError = !uiState.titleSuccessful,
                onValueChange = { onEvent(UpsertNoteEvent.OnNoteTitleChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(TestTags.TITLE_TEXT_FIELD)
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextFieldComponent(
                label = stringResource(id = R.string.description),
                singleLine = false,
                text = uiState.description,
                onValueChange = { onEvent(UpsertNoteEvent.OnNoteDescriptionChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .testTag(TestTags.DESCRIPTION_TEXT_FIELD)
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextFieldComponent(
                label = stringResource(id = R.string.due_date),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                },
                enabled = false,
                errorMessage = stringResource(id = R.string.due_date_error_message),
                text = uiState.dueDate,
                hasError = !uiState.dueDateSuccessful,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(TestTags.DUE_DATE_TEXT_FIELD)
                    .clickable {
                        shouldOpenDateDialog = true
                    }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onEvent(UpsertNoteEvent.OnUpsertNote) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.save_note),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}

@Preview
@Composable
private fun UpsertScreenPreview() {
    NotesTheme {
        UpsertNoteScreen(
            noteId = 1,
            onBack = {},
            uiState = UpsertNoteUiState(),
            onEvent = {}
        )
    }
}