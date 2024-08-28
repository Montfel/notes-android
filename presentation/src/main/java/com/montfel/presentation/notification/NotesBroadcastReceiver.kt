package com.montfel.presentation.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import com.montfel.domain.model.Note
import com.montfel.domain.repository.FcmRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.json.Json
import javax.inject.Inject

@AndroidEntryPoint
class NotesBroadcastReceiver : BroadcastReceiver() {
    @Inject
    lateinit var repository: FcmRepository

    override fun onReceive(context: Context, intent: Intent) {
        val scope = CoroutineScope(Dispatchers.IO)

        val noteJson = intent.getStringExtra(NotesAlarmManager.NOTE).orEmpty()
        val note = Json.decodeFromString<Note>(noteJson)

        scope.launch {
            val token = Firebase.messaging.token.await()

            runCatching {
                repository.sendNotification(token = token, note = note)
            }.getOrElse {
                it.printStackTrace()
            }
        }
    }
}