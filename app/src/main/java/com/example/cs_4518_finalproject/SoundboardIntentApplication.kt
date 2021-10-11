package com.example.cs_4518_finalproject

import android.app.Application

class SoundboardIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SoundRepository.initialize(this)
    }
}