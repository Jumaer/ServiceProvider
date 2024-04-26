package com.friendship.bhaibhaiclinic.hilt

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() , LifecycleObserver {

    override fun onCreate() {
        super.onCreate()

    }
}