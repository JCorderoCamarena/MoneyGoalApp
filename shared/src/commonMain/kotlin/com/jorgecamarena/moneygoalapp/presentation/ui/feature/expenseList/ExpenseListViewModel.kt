package com.jorgecamarena.moneygoalapp.presentation.ui.feature.expenseList

import cafe.adriel.voyager.core.model.coroutineScope
import com.jorgecamarena.moneygoalapp.data.domain.interactors.DeleteOneExpenseUseCase
import com.jorgecamarena.moneygoalapp.data.domain.interactors.GetAllExpensesUseCase
import com.jorgecamarena.moneygoalapp.presentation.model.ResourceUiState
import com.jorgecamarena.moneygoalapp.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class ExpenseListViewModel(
    val deleteOneExpenseUseCase: DeleteOneExpenseUseCase,
    val getAllExpensesUseCase: GetAllExpensesUseCase
): BaseViewModel<ExpenseListViewContract.Event, ExpenseListViewContract.State, ExpenseListViewContract.Effect>() {

    init {
        getExpenses()
    }

    override fun createInitialState(): ExpenseListViewContract.State =
        ExpenseListViewContract.State(expenseList = ResourceUiState.Idle)

    override fun handleEvent(event: ExpenseListViewContract.Event) {
        when(event) {
            is ExpenseListViewContract.Event.OnDeleteExpense -> deleteExpense(event.expenseId)
        }
    }

    private fun getExpenses() {
        setState { copy(expenseList = ResourceUiState.Loading) }
        coroutineScope.launch {
            getAllExpensesUseCase(Unit).collect {
                it.onSuccess {
                 setState {
                     copy(
                         expenseList =
                             if (it.isEmpty()) {
                                 ResourceUiState.Empty
                             } else {
                                 ResourceUiState.Success(it)
                             }
                     )
                 }
                }.onFailure {
                    setState { copy(expenseList = ResourceUiState.Error()) }
                }
            }
        }
    }

    private fun deleteExpense(expenseId: Long) {
        coroutineScope.launch {
            deleteOneExpenseUseCase(expenseId)
                .onSuccess {

                }
                .onFailure {

                }
        }
    }

}