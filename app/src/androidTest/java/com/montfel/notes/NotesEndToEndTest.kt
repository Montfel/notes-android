package com.montfel.notes

import android.content.Context
import android.os.Build
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.GrantPermissionRule
import com.montfel.presentation.theme.NotesTheme
import com.montfel.presentation.util.DateFormat
import com.montfel.presentation.util.TestTags
import com.montfel.presentation.util.formatDate
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

@HiltAndroidTest
class NotesEndToEndTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val permissionRule: GrantPermissionRule =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            GrantPermissionRule.grant(android.Manifest.permission.POST_NOTIFICATIONS)
        } else {
            GrantPermissionRule.grant()
        }

    @get:Rule(order = 2)
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
        runBlocking {
            val context = ApplicationProvider.getApplicationContext<Context>()

            val today = Date()

            val title = "Testando título"
            val description = "Testando descrição"
            val dueDate = today.formatDate(DateFormat.ONLY_DAY)
            val dueDateFormatted = today.formatDate(DateFormat.BRAZILIAN)
            val textAdded = "Editado "
            val editedTitle = textAdded + title
            val editedDescription = textAdded + description

            //Click in FAB to add a note
            composeTestRule
                .onNodeWithTag(TestTags.FAB)
                .performClick()

            // Add title and description
            composeTestRule
                .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
                .performTextInput(title)
            composeTestRule
                .onNodeWithTag(TestTags.DESCRIPTION_TEXT_FIELD)
                .performTextInput(description)

            // Add due date
            composeTestRule
                .onNodeWithTag(TestTags.DUE_DATE_TEXT_FIELD)
                .performClick()
            composeTestRule
                .onNodeWithText(text = dueDate, substring = true)
                .performClick()
            composeTestRule
                .onNodeWithText(context.getString(com.montfel.presentation.R.string.confirm))
                .performClick()

            //Click in save button
            composeTestRule
                .onNodeWithText(context.getString(com.montfel.presentation.R.string.save_note))
                .performClick()

            delay(500)

            //Check if note was saved
            composeTestRule
                .onNodeWithText(title)
                .assertExists()
            composeTestRule
                .onNodeWithText(description)
                .assertExists()
            composeTestRule
                .onNodeWithText(dueDateFormatted)
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

            delay(500)

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

            delay(500)

            //Check if note was deleted
            composeTestRule
                .onNodeWithText(editedTitle)
                .assertDoesNotExist()
            composeTestRule
                .onNodeWithText(editedDescription)
                .assertDoesNotExist()
        }
    }
}