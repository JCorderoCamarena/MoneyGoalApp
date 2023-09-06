package com.jorgecamarena

import android.app.Application
import data.di.appModule
import org.koin.core.context.startKoin

class MoneyGoalApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule())
        }
    }
}