package com.nropotov.dev.gamecenter

import android.app.Application
import com.nropotov.dev.gamecenter.di.DaggerAppComponent

class GamesApp : Application() {

    val component by lazy {
        DaggerAppComponent.factory().create()
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}
