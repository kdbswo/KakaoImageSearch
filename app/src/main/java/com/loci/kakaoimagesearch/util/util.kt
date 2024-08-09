package com.loci.kakaoimagesearch.util

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loci.kakaoimagesearch.data.remote.model.TotalEntity
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

fun convertStringToSearchImageEntity(data: String): List<TotalEntity>? {
    val gson = Gson()
    val itemType = object : TypeToken<List<TotalEntity>>() {}.type
    return gson.fromJson<List<TotalEntity>>(data, itemType)
}

fun jsonListToString(liveData: LiveData<List<TotalEntity>>): String {
    val gson = Gson()
    return gson.toJson(liveData.value)
}
