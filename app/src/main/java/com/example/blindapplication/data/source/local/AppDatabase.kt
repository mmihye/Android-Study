package com.example.blindapplication.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.blindapplication.data.model.entity.ContentEntity
import com.example.blindapplication.data.source.local.dao.ContentDao
import com.example.blindapplication.data.source.local.dao.DateConverter

@Database(entities = [ContentEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contentDao() : ContentDao
}