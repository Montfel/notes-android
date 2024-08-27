package com.montfel.presentation.notes

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.montfel.domain.model.Note
import com.montfel.presentation.util.formatDate
import com.montfel.presentation.util.toUTCDate
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotesScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun verifyIfNoteIsShown() {
        val note = Note(
            id = 1,
            title = "Note 1",
            description = "Description 1",
            dueDate = 6381
        )

        composeTestRule.setContent {
            NotesScreen(
                uiState = NotesUiState(
                    notes = listOf(note)
                ),
                onUpsertNote = {},
                onEvent = {}
            )
        }

        composeTestRule.onNodeWithText(note.title).assertExists()
        composeTestRule.onNodeWithText(note.description).assertExists()
        composeTestRule.onNodeWithText(note.dueDate.toUTCDate().formatDate()).assertExists()
    }
}