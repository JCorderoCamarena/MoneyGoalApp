package com.jorgecamarena.moneygoalapp.presentation.ui.addExpense

import cafe.adriel.voyager.core.model.coroutineScope
import com.jorgecamarena.moneygoalapp.data.domain.interactors.SaveExpenseUseCase
import com.jorgecamarena.moneygoalapp.entity.Expense
import com.jorgecamarena.moneygoalapp.presentation.model.ResourceUiState
import com.jorgecamarena.moneygoalapp.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock


class HomeViewModel (
    val saveExpenseUseCase: SaveExpenseUseCase
): BaseViewModel<HomeViewContract.Event, HomeViewContract.State, HomeViewContract.Effect>() {
    override fun createInitialState(): HomeViewContract.State =
        HomeViewContract.State(expenses = ResourceUiState.Idle)

    override fun handleEvent(event: HomeViewContract.Event) {
        when(event) {
            is HomeViewContract.Event.OnSaveExpense -> saveExpense(event.concept, event.amount)
        }
    }

    private fun saveExpense(concept: String, amount: Float) {
        coroutineScope.launch {
            saveExpenseUseCase(Expense(null, concept, amount, Clock.System.now().toEpochMilliseconds()))
                .onSuccess {
                    setState { copy( expenses = ResourceUiState.Success(listOf())) }
                    setEffect { HomeViewContract.Effect.ExpenseAdded }
                }
                .onFailure {
                    setState { copy(expenses = ResourceUiState.Error()) }
                }
        }
    }

}