package com.example.memesfeed.di

import android.app.Application
import com.example.memesfeed.di.component.AppComponent
import com.example.memesfeed.di.component.DaggerAppComponent

class App: Application() {

    companion object {
        private lateinit var component: AppComponent
        fun getComponent() = component
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().application(this).build()
    }

}