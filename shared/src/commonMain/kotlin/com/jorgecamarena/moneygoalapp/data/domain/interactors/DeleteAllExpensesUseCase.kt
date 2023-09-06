package com.jorgecamarena.moneygoalapp.data.domain.interactors

import com.jorgecamarena.moneygoalapp.data.domain.ExpenseRepository
import com.jorgecamarena.moneygoalapp.data.domain.interactors.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class DeleteAllExpensesUseCase(
    private val repository: ExpenseRepository,
    dispatcher: CoroutineDispatcher
): BaseUseCase<Unit, Unit>(dispatcher) {
    override suspend fun block(param: Unit) = repository.deleteAllExpenses()
}