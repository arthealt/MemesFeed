package com.example.memesfeed.di.component

import android.app.Application
import com.example.memesfeed.application.activity.LoginActivity
import com.example.memesfeed.di.module.AppModule
import com.example.memesfeed.di.module.NetworkModule
import com.example.memesfeed.di.module.StorageModule
import com.example.memesfeed.di.scope.PerApplication
import dagger.BindsInstance
import dagger.Component

@PerApplication
@Component(modules = [AppModule::class, NetworkModule::class, StorageModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(loginActivity: LoginActivity)

}