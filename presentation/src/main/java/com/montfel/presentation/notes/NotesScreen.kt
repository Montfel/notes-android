package com.montfel.presentation.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.montfel.domain.model.Note
import com.montfel.presentation.R
import com.montfel.presentation.notes.components.NoteCard
import com.montfel.presentation.theme.NotesTheme
import com.montfel.presentation.util.TestTags

@Composable
fun NotesRoute(
    onUpsertNote: (Note?) -> Unit,
) {
    val viewModel: NotesViewModel = hiltViewModel()
    val uiState by viewModel.uiState.observeAsState()

    NotesScreen(
        uiState = uiState,
        onUpsertNote = onUpsertNote,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    uiState: NotesUiState?,
    onUpsertNote: (Note?) -> Unit,
    onEvent: (NotesEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onUpsertNote(null) },
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.testTag(TestTags.FAB)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) { paddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
                .fillMaxSize()
        ) {
            uiState?.notes?.let { notes ->
                items(notes) {
                    NoteCard(
                        note = it,
                        onEdit = onUpsertNote,
                        onDelete = { note -> onEvent(NotesEvent.DeleteNote(note)) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun NotesScreenPreview() {
    NotesTheme {
        NotesScreen(
            uiState = NotesUiState(),
            onEvent = {},
            onUpsertNote = {}
        )
    }
}

