package com.jorgecamarena.moneygoalapp.data.repository

import com.jorgecamarena.moneygoalapp.data.database.CommonDatabase
import com.jorgecamarena.moneygoalapp.data.domain.ExpenseRepository
import com.jorgecamarena.moneygoalapp.entity.Expense
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExpenseRepositoryImp(
    private val commonDatabase: CommonDatabase
): ExpenseRepository {
    override suspend fun getExpenses(): Flow<List<Expense>> =
        commonDatabase { appDatabase ->
            appDatabase.appDatabaseQueries.selectAllExpenses(::mapExpense).asFlow().map {
                it.executeAsList()
            }
        }


    override suspend fun saveExpense(expense: Expense) {
        commonDatabase {
            it.appDatabaseQueries.insertExpense(
                id = expense.id,
                concept = expense.concept,
                amount = expense.amount,
                timestamp = expense.timestamp
            )
        }
    }

    override suspend fun deleteAllExpenses() {
        commonDatabase {
            it.appDatabaseQueries.removeAllExpenses()
        }
    }

    override suspend fun deleteOneExpense(id: Long) {
        commonDatabase {
            it.appDatabaseQueries.removeExpense(id)
        }
    }

    private fun mapExpense(
        id: Long,
        concept: String,
        amount: Float,
        timestamp: Long
    ): Expense {
        return Expense(id, concept, amount, timestamp)
    }
}