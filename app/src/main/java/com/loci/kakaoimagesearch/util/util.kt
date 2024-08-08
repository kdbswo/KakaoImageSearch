package com.loci.kakaoimagesearch.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun dateToStringFormat(date: Date?): String {
    val dateFormat = "yyyy-MM-dd HH:mm:ss"
    val simpleDataFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
    return date?.let { simpleDataFormat.format(it) }.toString()
}

fun stringFormatToDate(stringDate: String): Date? {
    val dateFormat = "yyyy-MM-dd HH:mm:ss"
    val simpleDataFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
    return simpleDataFormat.parse(stringDate)
}


