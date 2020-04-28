package com.example.memesfeed.data.remote.services

import com.example.memesfeed.data.remote.models.MemDto
import retrofit2.http.GET

interface MemesService {

    @GET("memes")
    suspend fun getMemes(): MemDto

}