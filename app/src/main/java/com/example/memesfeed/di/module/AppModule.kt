package com.example.memesfeed.di.module

import android.app.Application
import com.example.memesfeed.application.utils.NetworkUtil
import com.example.memesfeed.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @PerApplication
    fun provideUtil(context: Application): NetworkUtil = NetworkUtil(context)

}