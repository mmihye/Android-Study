package com.example.blindapplication.di

import com.example.blindapplication.data.repository.ContentRepositoryImpl
import com.example.blindapplication.data.source.local.dao.ContentDao
import com.example.blindapplication.data.source.remote.api.ContentService
import com.example.blindapplication.domain.repository.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesContantRepository(
        contentService: ContentService,
        contentDao: ContentDao
    ): ContentRepository = ContentRepositoryImpl(contentService, contentDao)
}