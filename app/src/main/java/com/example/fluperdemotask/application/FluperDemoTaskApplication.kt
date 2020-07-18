package com.example.fluperdemotask.application

import android.app.Application
import com.example.fluperdemotask.module.roomModule
import com.example.fluperdemotask.module.viewModelModule
import org.koin.android.ext.android.startKoin

/**
 * Application class to get the context from anywhere in the application
 */
class FluperDemoTaskApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            roomModule,
            viewModelModule
        ))
    }
}