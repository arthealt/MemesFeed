package com.example.memesfeed.data.remote

import com.example.memesfeed.data.remote.models.AuthInfoDto
import com.example.memesfeed.data.remote.models.MemDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun auth(@Field("login") login: String, @Field("password") password: String): AuthInfoDto

    @GET("memes")
    suspend fun getMemes(): MemDto

}