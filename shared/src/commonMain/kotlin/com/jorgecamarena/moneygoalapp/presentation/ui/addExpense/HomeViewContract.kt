package com.jorgecamarena.moneygoalapp.presentation.ui.addExpense

import com.jorgecamarena.moneygoalapp.entity.Expense
import com.jorgecamarena.moneygoalapp.presentation.model.ResourceUiState
import com.jorgecamarena.moneygoalapp.presentation.mvi.UiEffect
import com.jorgecamarena.moneygoalapp.presentation.mvi.UiEvent
import com.jorgecamarena.moneygoalapp.presentation.mvi.UiState

interface HomeViewContract {

    sealed interface Event: UiEvent {
        data class OnSaveExpense(val concept: String, val amount: Float): Event
//        data class OnDeleteExpense(val expenseId: Long): Event

    }

    data class State(
        val expenses: ResourceUiState<List<Expense>>
    ): UiState

    sealed interface Effect : UiEffect {
        data object ExpenseAdded : Effect
    }
}