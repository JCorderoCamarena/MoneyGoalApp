package com.jorgecamarena.moneygoalapp.presentation.ui.feature.addExpense

import com.jorgecamarena.moneygoalapp.entity.Expense
import com.jorgecamarena.moneygoalapp.presentation.model.ResourceUiState
import com.jorgecamarena.moneygoalapp.presentation.mvi.UiEffect
import com.jorgecamarena.moneygoalapp.presentation.mvi.UiEvent
import com.jorgecamarena.moneygoalapp.presentation.mvi.UiState

interface HomeViewContract {

    sealed interface Event: UiEvent {
        data object OnSaveExpense : Event
        data class OnUpdateConcept(val concept: String): Event
        data class OnUpdateAmount(val amount: Float): Event
    }

    data class State(
        val concept: String,
        val amount: Float
    ): UiState

    sealed interface Effect : UiEffect {
        data object ExpenseAdded : Effect
    }
}