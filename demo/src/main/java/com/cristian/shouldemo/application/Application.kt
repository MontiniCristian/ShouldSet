package com.cristian.shouldemo.application

import android.app.Application
import com.cristian.shouldset.manager.ShouldManager

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        ShouldManager.init(this)
    }
}