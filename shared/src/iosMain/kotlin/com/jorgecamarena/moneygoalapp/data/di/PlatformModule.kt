package com.jorgecamarena.moneygoalapp.data.di

import com.jorgecamarena.moneygoalapp.data.database.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory() }
}