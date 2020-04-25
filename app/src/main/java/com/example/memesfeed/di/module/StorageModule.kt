package com.example.memesfeed.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.memesfeed.application.helpers.UserInfoDB
import com.example.memesfeed.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    @PerApplication
    fun provideSharedPreferences(context: Application): SharedPreferences =
        context.getSharedPreferences(UserInfoDB.USER_INFO, Context.MODE_PRIVATE)

}