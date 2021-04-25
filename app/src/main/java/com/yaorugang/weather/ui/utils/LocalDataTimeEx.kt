package com.yaorugang.weather.ui.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatAsDefault(): String {
    return format(DateTimeFormatter.ofPattern("yyyy-MM-dd',' HH:mm"))
}