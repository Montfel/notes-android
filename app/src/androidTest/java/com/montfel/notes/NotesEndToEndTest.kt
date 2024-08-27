package com.montfel.notes

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import com.montfel.presentation.theme.NotesTheme
import com.montfel.presentation.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NotesEndToEndTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)


    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        composeTestRule.activity.setContent {
            NotesTheme {
                NavigationComponent()
            }
        }
    }

    @Test
    fun saveEditAndDeleteNote() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val newTitle = "Testando título"
        val newDescription = "Testando descrição"
        val textAdded = "Editado "
        val editedTitle = textAdded + newTitle
        val editedDescription = textAdded + newDescription

        //Click in FAB to add a note
        composeTestRule
            .onNodeWithTag(TestTags.FAB)
            .performClick()

        // Add title and description
        composeTestRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput(newTitle)
        composeTestRule
            .onNodeWithTag(TestTags.DESCRIPTION_TEXT_FIELD)
            .performTextInput(newDescription)

        //Click in save button
        composeTestRule
            .onNodeWithText(context.getString(com.montfel.presentation.R.string.save_note))
            .performClick()

        //Check if note was saved
        composeTestRule
            .onNodeWithText(newTitle)
            .assertExists()
        composeTestRule
            .onNodeWithText(newDescription)
            .assertExists()

        //Click to edit note
        composeTestRule
            .onNodeWithTag(TestTags.EDIT_BUTTON)
            .performClick()

        //Edit title and description
        composeTestRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput(textAdded)
        composeTestRule
            .onNodeWithTag(TestTags.DESCRIPTION_TEXT_FIELD)
            .performTextInput(textAdded)

        //Click in save button
        composeTestRule
            .onNodeWithText(context.getString(com.montfel.presentation.R.string.save_note))
            .performClick()

        //Check if note was edited
        composeTestRule
            .onNodeWithText(editedTitle)
            .assertExists()
        composeTestRule
            .onNodeWithText(editedDescription)
            .assertExists()

        //Click to delete note
        composeTestRule
            .onNodeWithTag(TestTags.DELETE_BUTTON)
            .performClick()

        //Check if note was deleted
        composeTestRule
            .onNodeWithText(editedTitle)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(editedDescription)
            .assertDoesNotExist()
    }
}