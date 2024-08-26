package com.montfel.notes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.montfel.presentation.addeditnote.AddEditNoteScreen
import com.montfel.presentation.notes.NotesScreen

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Notes) {
        composable<Screen.Notes> {
            NotesScreen(
                onAddNote = { navController.navigate(Screen.AddEditNote()) },
                onEditNote = { note -> navController.navigate(Screen.AddEditNote(note.id)) }
            )
        }

        composable<Screen.AddEditNote> {
            val (noteId) = it.toRoute<Screen.AddEditNote>()

            AddEditNoteScreen(
                noteId = noteId,
                onBack = navController::popBackStack
            )
        }
    }
}