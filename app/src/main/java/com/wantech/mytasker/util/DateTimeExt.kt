package com.wantech.mytasker.util

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDate(format: String = "h:mm a"): String {
    val dateFormat = SimpleDateFormat(/* pattern = */ format, /* locale = */ Locale.ENGLISH)
    val date = Date(/* date = */ this)
    return dateFormat.format(/* date = */ date)
}