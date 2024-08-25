package com.montfel.presentation.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.montfel.domain.model.Note
import com.montfel.presentation.theme.NotesTheme
import com.montfel.presentation.util.formatDate
import com.montfel.presentation.util.toUTCDate
import java.util.Date

@Composable
fun NoteItem(
    note: Note,
    onEdit: (Note) -> Unit,
    onDelete: (Note) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(text = note.title)

                Text(text = note.description)

                Text(text = note.dueDate.toUTCDate().formatDate())
            }

            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                IconButton(onClick = { onEdit(note) }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }

                IconButton(onClick = { onDelete(note) }) {
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
        NoteItem(
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