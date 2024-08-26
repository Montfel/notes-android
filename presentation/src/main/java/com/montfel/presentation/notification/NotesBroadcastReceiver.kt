package com.montfel.presentation.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.montfel.domain.model.Note
import kotlinx.serialization.json.Json

class NotesBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val noteJson = intent.getStringExtra(NotesAlarmManager.NOTE).orEmpty()
        val note = Json.decodeFromString<Note>(noteJson)

        NotificationComponent(context).showNotification(note)
    }
}