package com.example.pinterestrealniyclone

import android.app.Application
import android.util.Log

class PinterestApplication : Application() {

    companion object {
        private const val TAG: String = "PinterestApplication"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }
}
