package com.montfel.presentation.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.montfel.domain.model.Note
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class NotesAlarmManager(private val context: Context) {
    fun setUpAlarm(note: Note) {
        val intent = Intent(context, NotesBroadcastReceiver::class.java).apply {
            putExtra(NOTE, Json.encodeToString(note))
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            note.id ?: 0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        runCatching {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                note.dueDate,
                pendingIntent
            )
        }.getOrElse {
            it.printStackTrace()
        }
    }

    fun cancelAlarm(note: Note) {
        val intent = Intent(context, NotesBroadcastReceiver::class.java).apply {
            putExtra(NOTE, Json.encodeToString(note))
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            note.id ?: 0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        runCatching {
            alarmManager.cancel(pendingIntent)
        }.getOrElse {
            it.printStackTrace()
        }
    }

    companion object {
        const val NOTE = "NOTE"
    }
}