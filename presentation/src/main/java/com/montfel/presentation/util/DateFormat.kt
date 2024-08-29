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
        set(Calendar.MILLISECOND, 0)
        timeZone = TimeZone.getDefault()
    }
    return calendar.time
}

fun Date.toLongWithTimeZero(): Long {
    val calendar = Calendar.getInstance().apply {
        time = this@toLongWithTimeZero
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return calendar.time.time
}

fun Long.toUTC(): Long {
    val utcTimeZone = TimeZone.getTimeZone("UTC")
    val calendar = Calendar.getInstance(utcTimeZone).apply {
        time = Date(this@toUTC)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
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

fun Date.formatDate(dateFormat: String): String {
    return SimpleDateFormat(dateFormat, Locale.getDefault()).format(this@formatDate)
}

object DateFormat {
    const val BRAZILIAN = "dd/MM/yyyy"
    const val ONLY_DAY = "d"
}