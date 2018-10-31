package com.oleg.mynotes.data

import android.arch.persistence.room.TypeConverter
import android.util.Log

class DateConverter {
    @TypeConverter
    fun dateToDatestamp(date: Date): String?{
        return date.toString()
    }

    @TypeConverter
    fun datestampToDate(value: String?): Date?{
        return try {
            val list: List<String>? = value?.split(",")
            Date(
                    year = list!![0].toInt(),
                    month = list[1].toInt(),
                    dayOfMonth = list[2].toInt(),
                    hour = list[3].toInt(),
                    minute = list[4].toInt())
        }catch (ex: NumberFormatException){
            Date(1998, 9, 10, 12, 13)
        }

    }
}