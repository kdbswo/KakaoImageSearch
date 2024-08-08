package com.loci.kakaoimagesearch.util

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
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

fun convertStringToSearchImageEntity(data: String): List<SearchImageEntity>? {
    val gson = Gson()
    val itemType = object : TypeToken<List<SearchImageEntity>>() {}.type
    return gson.fromJson<List<SearchImageEntity>>(data, itemType)
}

fun jsonListToString(liveData: LiveData<List<SearchImageEntity>>): String {
    val gson = Gson()
    return gson.toJson(liveData.value)
}
