package com.loci.kakaoimagesearch.util

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
import com.loci.kakaoimagesearch.data.remote.model.SearchImageListEntity
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


