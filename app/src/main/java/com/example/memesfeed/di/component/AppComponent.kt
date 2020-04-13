package com.example.memesfeed.di.component

import com.example.memesfeed.LoginActivity
import com.example.memesfeed.di.module.NetworkModule
import com.example.memesfeed.di.scope.PerApplication
import dagger.Component

@PerApplication
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(loginActivity: LoginActivity)

}