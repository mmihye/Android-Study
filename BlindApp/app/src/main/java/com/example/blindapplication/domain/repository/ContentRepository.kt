package com.example.blindapplication.domain.repository

import com.example.blindapplication.domain.model.Content

interface ContentRepository {

    suspend fun save(item: Content) :Boolean
}