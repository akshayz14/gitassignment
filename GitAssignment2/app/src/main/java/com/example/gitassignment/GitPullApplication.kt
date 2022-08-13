package com.example.gitassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitPullApplication : Application() {


    companion object {
        lateinit var INSTANCE: GitPullApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}