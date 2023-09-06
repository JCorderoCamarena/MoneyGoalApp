package data.di

import data.database.Database
import data.database.DatabaseDriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val platformModules = module {
//    singleOf(::DatabaseDriverFactory)
//    singleOf(::Database)
}

fun appModule() = listOf(platformModules)