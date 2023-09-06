package com.jorgecamarena.moneygoalapp.data.database

import com.jorgecamarena.moneygoalapp.database.AppDatabase
import com.jorgecamarena.moneygoalapp.entity.Expense

class CommonDatabase(
    private val databaseDriverFactory: DatabaseDriverFactory
) {
    private var database: AppDatabase? = null
//    private val dbQuery = database.appDatabaseQueries


//    internal fun getAllExpenses(): List<Expense> {
//        return dbQuery.selectAllExpenses(::mapExpense).executeAsList()
//    }
//
//    internal fun removeAllExpenses() {
//        dbQuery.removeAllExpenses()
//    }
//
//    internal fun insertExpense(expense: Expense) {
//        with(expense) {
//            dbQuery.insertExpense(
//                id = null,
//                concept = concept,
//                amount = amount,
//                timestamp = timestamp
//            )
//        }
//    }

    private fun mapExpense(
        id: Long,
        concept: String,
        amount: Float,
        timestamp: Long
    ): Expense {
        return Expense(id, concept, amount, timestamp)
    }

    private suspend fun initDatabase() {
        if (database == null) {
            database = AppDatabase.invoke(
                databaseDriverFactory.createDriver()
            )
        }
    }

    suspend operator fun <R> invoke(block: suspend (AppDatabase) -> R): R {
        initDatabase()
        return block(database!!)
    }
}