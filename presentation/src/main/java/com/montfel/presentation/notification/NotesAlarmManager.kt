package com.montfel.presentation.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.montfel.domain.model.Note
import com.montfel.presentation.util.minusOneDay
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class NotesAlarmManager(private val context: Context) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun setUpAlarm(note: Note) {
        val pendingIntent = createPendingIntent(note)

        runCatching {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                note.dueDate.minusOneDay(),
                pendingIntent
            )
        }.getOrElse {
            it.printStackTrace()
        }
    }

    fun cancelAlarm(note: Note) {
        val pendingIntent = createPendingIntent(note)

        runCatching {
            alarmManager.cancel(pendingIntent)
        }.getOrElse {
            it.printStackTrace()
        }
    }

    private fun createPendingIntent(note: Note): PendingIntent {
        val intent = Intent(context, NotesBroadcastReceiver::class.java).apply {
            putExtra(NOTE, Json.encodeToString(note))
        }

        return PendingIntent.getBroadcast(
            context,
            note.id ?: 0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    companion object {
        const val NOTE = "NOTE"
    }
}