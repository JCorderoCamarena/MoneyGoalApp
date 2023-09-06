package com.jorgecamarena.moneygoalapp.data.domain.interactors

import com.jorgecamarena.moneygoalapp.data.domain.ExpenseRepository
import com.jorgecamarena.moneygoalapp.data.domain.interactors.type.BaseUseCaseFlow
import com.jorgecamarena.moneygoalapp.entity.Expense
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetAllExpensesUseCase(
    private val repository: ExpenseRepository,
    dispatcher: CoroutineDispatcher
): BaseUseCaseFlow<Unit, List<Expense>>(dispatcher) {
    override suspend fun build(param: Unit): Flow<List<Expense>> = repository.getExpenses()
}