package com.example.memesfeed.di.module

import android.app.Application
import android.content.Context
import com.example.memesfeed.application.helpers.Util
import com.example.memesfeed.di.App
import com.example.memesfeed.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @PerApplication
    fun provideUtil(context: Application): Util = Util(context)

}