package com.montfel.presentation.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.montfel.domain.model.Note
import com.montfel.presentation.theme.NotesTheme
import com.montfel.presentation.util.DateFormat
import com.montfel.presentation.util.TestTags
import com.montfel.presentation.util.formatDate
import com.montfel.presentation.util.toUTCDate
import java.util.Date

@Composable
fun NoteCard(
    note: Note,
    onEdit: (Note) -> Unit,
    onDelete: (Note) -> Unit,
) {
    val iconButtonColors = IconButtonDefaults.iconButtonColors(
        contentColor = MaterialTheme.colorScheme.secondary
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = note.description,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = note.dueDate.toUTCDate().formatDate(DateFormat.BRAZILIAN),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxHeight()
            ) {
                IconButton(
                    onClick = { onEdit(note) },
                    colors = iconButtonColors,
                    modifier = Modifier.testTag(TestTags.EDIT_BUTTON)
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }

                IconButton(
                    onClick = { onDelete(note) },
                    colors = iconButtonColors,
                    modifier = Modifier.testTag(TestTags.DELETE_BUTTON)
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
        }
    }
}

@Preview
@Composable
private fun NoteItemPreview() {
    NotesTheme {
        NoteCard(
            note = Note(
                title = "Title",
                description = "Description",
                dueDate = Date().time
            ),
            onDelete = {},
            onEdit = {}
        )
    }
}