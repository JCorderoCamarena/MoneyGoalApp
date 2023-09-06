package data.database

import com.jorgecamarena.moneygoalapp.database.AppDatabase
import entity.Expense

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries


    internal fun getAllExpenses(): List<Expense> {
        return dbQuery.selectAllExpenses(::mapExpense).executeAsList()
    }

    internal fun removeAllExpenses() {
        dbQuery.removeAllExpenses()
    }

    internal fun insertExpense(expense: Expense) {
        with(expense) {
            dbQuery.insertExpense(
                id = null,
                concept = concept,
                amount = amount,
                timestamp = timestamp
            )
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