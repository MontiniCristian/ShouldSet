package com.cristian.shouldemo.application

import android.app.Application
import android.content.Context
import com.cristian.shouldset.manager.ShouldManager

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        ShouldManager.init(getSharedPreferences(packageName+"_pref", Context.MODE_PRIVATE))
    }
}