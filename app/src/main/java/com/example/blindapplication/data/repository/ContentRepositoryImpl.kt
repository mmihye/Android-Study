package com.example.blindapplication.data.repository

import com.example.blindapplication.data.model.ContentMapper.toEntity
import com.example.blindapplication.data.model.ContentMapper.toRequest
import com.example.blindapplication.data.source.local.dao.ContentDao
import com.example.blindapplication.data.source.remote.api.ContentService
import com.example.blindapplication.domain.model.Content
import com.example.blindapplication.domain.repository.ContentRepository
import java.io.IOException
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService,
    private val contentDao: ContentDao,
) : ContentRepository{

    override suspend fun save(item: Content): Boolean {
        return try{
            contentService.saveItem(item.toRequest())
            contentDao.insert(item.toEntity())
            true
        } catch (e : IOException){
            false
        }
    }
}