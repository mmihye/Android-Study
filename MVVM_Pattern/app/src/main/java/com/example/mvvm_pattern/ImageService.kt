package com.example.mvvm_pattern

import com.example.mvvm_pattern.model.ImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {

    @Headers("Authorization: Client-Id PXguPz_PnG_MEsXHUhK7tly1d9Kb8VgnWAzrCxxz6qA")
    @GET("photos/random")
    fun getRandomImageRx() : Single<ImageResponse>
}