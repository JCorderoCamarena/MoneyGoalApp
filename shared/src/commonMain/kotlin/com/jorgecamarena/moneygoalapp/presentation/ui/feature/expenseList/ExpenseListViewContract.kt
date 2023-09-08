package com.jorgecamarena.moneygoalapp.presentation.ui.feature.expenseList

import com.jorgecamarena.moneygoalapp.entity.Expense
import com.jorgecamarena.moneygoalapp.presentation.model.ResourceUiState
import com.jorgecamarena.moneygoalapp.presentation.mvi.UiEffect
import com.jorgecamarena.moneygoalapp.presentation.mvi.UiEvent
import com.jorgecamarena.moneygoalapp.presentation.mvi.UiState

interface ExpenseListViewContract {
    sealed interface Event: UiEvent {
        data class OnDeleteExpense(val expenseId: Long): Event
    }

    data class State(
        val expenseList: ResourceUiState<List<Expense>>
    ): UiState

    sealed interface Effect : UiEffect {

    }
}