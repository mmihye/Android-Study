package com.example.mvvm_pattern.repository

import com.example.mvvm_pattern.model.Image
import io.reactivex.Single

interface ImageRepository {

    fun getRandomImage():Single<Image>
}