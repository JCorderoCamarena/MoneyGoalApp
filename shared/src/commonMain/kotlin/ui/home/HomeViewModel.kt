package ui.home

import data.database.Database
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import entity.Expense
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class HomeViewModel (
    val database: Database
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(expenses = emptyList()))
    val uiState = _uiState.asStateFlow()

    fun loadName() {
        viewModelScope.launch {
            loadExpenses()
            delay(5_000)
            _uiState.update {
                it.copy(
                    name = "Jorge :D"
                )
            }
        }
    }

    fun saveExpense(concept: String, amount: Float) {
        viewModelScope.launch {
            database.insertExpense(
                Expense(null, concept, amount, Clock.System.now().toEpochMilliseconds())
            )
            loadExpenses()
        }
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            val listOfExpenses = database.getAllExpenses().toMutableList()
            if (listOfExpenses.isEmpty()) {
                listOfExpenses.add(element = Expense(99, "Fake", 0f, 123456))
            }
            _uiState.update {
                it.copy(
                    expenses = listOfExpenses
                )
            }
        }
    }

    fun deleteAllExpenses() {
        viewModelScope.launch {
            database.removeAllExpenses()
            loadExpenses()
        }
    }

}

data class HomeUiState(
    val name: String? = null,
    val expenses: List<Expense>
)