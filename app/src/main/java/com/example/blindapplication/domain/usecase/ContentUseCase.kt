package com.example.blindapplication.domain.usecase

import com.example.blindapplication.domain.model.Content
import com.example.blindapplication.domain.repository.ContentRepository
import javax.inject.Inject

class ContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun save(item: Content) = contentRepository.save(item)
}