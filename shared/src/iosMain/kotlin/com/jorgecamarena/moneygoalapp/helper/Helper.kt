package com.jorgecamarena.moneygoalapp.helper

import data.di.appModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}