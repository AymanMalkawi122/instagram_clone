package com.example.instagram_clone

import android.app.Application
import android.util.Log

class BaseApp: Application() {
    private val TAG = "BaseApp"
    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "init launched")
        SharedPrefManager.init(this)
    }
}