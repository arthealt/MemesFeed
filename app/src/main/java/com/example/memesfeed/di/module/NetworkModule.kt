package com.example.memesfeed.di.module

import com.example.memesfeed.data.remote.services.AuthService
import com.example.memesfeed.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @PerApplication
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().build()

    @Provides
    @PerApplication
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://demo2407529.mockable.io/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @PerApplication
    fun provideApiService(retrofit: Retrofit): AuthService = retrofit.create(
        AuthService::class.java)

}