package com.moath.aqua_pulse.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    private var dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS", Locale.getDefault())
    private var dateOnlyFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun formatDateTime(timestamp: Long): String {
        return dateTimeFormat.format(Date(timestamp))
    }

    fun formatDateOnly(timestamp: Long): String {
        return dateOnlyFormat.format(Date(timestamp))
    }

    fun parseDateTime(dateString: String): Long {
        return dateTimeFormat.parse(dateString)?.time ?: 0L
    }

    fun getCurrentDateTime(): String {
        return dateTimeFormat.format(Date())
    }

    fun getCurrentDateOnly(): String {
        return dateOnlyFormat.format(Date())
    }
}