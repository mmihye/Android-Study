package com.example.blindapplication.di

import android.content.Context
import androidx.room.Room
import com.example.blindapplication.data.source.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext context: Context):AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java,"blidApp.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}