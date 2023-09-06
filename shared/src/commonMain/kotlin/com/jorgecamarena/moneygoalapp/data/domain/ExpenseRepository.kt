package com.jorgecamarena.moneygoalapp.data.domain

import com.jorgecamarena.moneygoalapp.entity.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun getExpenses(): Flow<List<Expense>>
    suspend fun saveExpense(expense: Expense)
    suspend fun deleteAllExpenses()
    suspend fun deleteOneExpense(id: Long)
}