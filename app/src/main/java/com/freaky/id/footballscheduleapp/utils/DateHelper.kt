package com.freaky.id.footballscheduleapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun formatDateToMatch(date: Date): String {
        return SimpleDateFormat("EEE, dd MMM yyy", Locale.getDefault()).format(date)
    }
}