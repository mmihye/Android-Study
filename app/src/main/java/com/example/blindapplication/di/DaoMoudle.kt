package com.example.blindapplication.di

import com.example.blindapplication.data.source.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoMoudle {

    @Singleton
    @Provides
    fun providesContentDao(appDatabase: AppDatabase) = appDatabase.contentDao()
}