package com.montfel.presentation.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.toUTCDate(): Date {
    val utcTimeZone = TimeZone.getTimeZone("UTC")
    val calendar = Calendar.getInstance(utcTimeZone).apply {
        time = Date(this@toUTCDate)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        timeZone = TimeZone.getDefault()
    }
    return calendar.time
}

fun Date.toUTCLong(): Long {
    val utcTimeZone = TimeZone.getTimeZone("UTC")
    val calendar = Calendar.getInstance(utcTimeZone).apply {
        time = this@toUTCLong
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        timeZone = TimeZone.getDefault()
    }
    return calendar.time.time
}

fun Long.minusOneDay(): Long {
    val calendar = Calendar.getInstance().apply {
        time = Date(this@minusOneDay)
        set(Calendar.DAY_OF_YEAR, get(Calendar.DAY_OF_YEAR) - 1)
    }
    return calendar.time.time
}

fun Date.formatDate(): String {
    val calendar = Calendar.getInstance(Locale.getDefault()).apply { time = this@formatDate }
    val date = calendar.time

    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return simpleDateFormat.format(date)
}