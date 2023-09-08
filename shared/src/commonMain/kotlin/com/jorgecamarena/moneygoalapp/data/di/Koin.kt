package com.jorgecamarena.moneygoalapp.data.di

import com.jorgecamarena.moneygoalapp.data.database.CommonDatabase
import com.jorgecamarena.moneygoalapp.data.domain.ExpenseRepository
import com.jorgecamarena.moneygoalapp.data.domain.interactors.DeleteAllExpensesUseCase
import com.jorgecamarena.moneygoalapp.data.domain.interactors.DeleteOneExpenseUseCase
import com.jorgecamarena.moneygoalapp.data.domain.interactors.GetAllExpensesUseCase
import com.jorgecamarena.moneygoalapp.data.domain.interactors.SaveExpenseUseCase
import com.jorgecamarena.moneygoalapp.data.repository.ExpenseRepositoryImp
import com.jorgecamarena.moneygoalapp.presentation.ui.feature.addExpense.HomeViewModel
import com.jorgecamarena.moneygoalapp.presentation.ui.feature.expenseList.ExpenseListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            repositoryModule,
            sqlDelightModule,
            dispatcherModule,
            useCaseModule,
            viewModelModule,
            platformModule()
        )
    }

val viewModelModule = module {
    factory { HomeViewModel(get()) }
    factory { ExpenseListViewModel(get(), get()) }
}

val useCaseModule = module {
    factory { GetAllExpensesUseCase(get(), get()) }
    factory { SaveExpenseUseCase(get(), get()) }
    factory { DeleteAllExpensesUseCase(get(), get()) }
    factory { DeleteOneExpenseUseCase(get(), get()) }
}

val repositoryModule = module {
    single<ExpenseRepository> { ExpenseRepositoryImp(get()) }
}

val sqlDelightModule = module {
    single { CommonDatabase(get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

fun initKoin() = initKoin {}