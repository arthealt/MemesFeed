package com.example.memesfeed.data.remote

import com.example.memesfeed.data.remote.models.AuthInfoDto
import com.example.memesfeed.data.remote.models.LoginUserRequestDto
import com.example.memesfeed.data.remote.models.MemDto
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun auth(@Body user: LoginUserRequestDto): AuthInfoDto

    @GET("memes")
    suspend fun getMemes(): MemDto

}