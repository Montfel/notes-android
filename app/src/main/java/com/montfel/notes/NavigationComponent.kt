package com.montfel.notes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.montfel.presentation.upsertnote.UpsertNoteRoute
import com.montfel.presentation.notes.NotesRoute

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Notes) {
        composable<Screen.Notes> {
            NotesRoute(
                onUpsertNote = { note -> navController.navigate(Screen.UpsertNote(note?.id)) }
            )
        }

        composable<Screen.UpsertNote> {
            val (noteId) = it.toRoute<Screen.UpsertNote>()

            UpsertNoteRoute(
                noteId = noteId,
                onBack = navController::popBackStack
            )
        }
    }
}