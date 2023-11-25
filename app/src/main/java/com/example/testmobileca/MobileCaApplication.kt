package com.example.testmobileca

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MobileCaApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: MobileCaApplication
        fun getInstance(): MobileCaApplication = instance
    }
}