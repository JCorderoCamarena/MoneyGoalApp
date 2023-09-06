package com.jorgecamarena.moneygoalapp.data.domain.interactors

import com.jorgecamarena.moneygoalapp.data.domain.ExpenseRepository
import com.jorgecamarena.moneygoalapp.data.domain.interactors.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class DeleteOneExpenseUseCase(
    private val repository: ExpenseRepository,
    coroutineDispatcher: CoroutineDispatcher
): BaseUseCase<Long, Unit>(coroutineDispatcher) {
    override suspend fun block(param: Long) = repository.deleteOneExpense(param)
}