package com.jorgecamarena.moneygoalapp.data.domain.interactors

import com.jorgecamarena.moneygoalapp.data.domain.ExpenseRepository
import com.jorgecamarena.moneygoalapp.data.domain.interactors.type.BaseUseCase
import com.jorgecamarena.moneygoalapp.entity.Expense
import kotlinx.coroutines.CoroutineDispatcher

class SaveExpenseUseCase(
    private val repository: ExpenseRepository,
    dispatcher: CoroutineDispatcher
): BaseUseCase<Expense, Unit>(dispatcher) {
    override suspend fun block(param: Expense) = repository.saveExpense(param)
}