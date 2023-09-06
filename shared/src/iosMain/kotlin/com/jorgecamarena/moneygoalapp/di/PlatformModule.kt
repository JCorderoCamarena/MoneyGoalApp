package com.jorgecamarena.moneygoalapp.di

import com.jorgecamarena.moneygoalapp.data.database.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModules(): Module = module {
    single { DatabaseDriverFactory() }
}