package com.example.blindapplication.data.source.local.dao

import androidx.room.TypeConverter
import com.example.blindapplication.util.DateUtil
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(timestamp:String?): Date? {
        return timestamp?.let{ DateUtil.dbDateFormat.parse(it)}
    }

    @TypeConverter
    fun toTimeStamp(date: Date?):String?{
        return DateUtil.dbDateFormat.format(date)
    }
}