package com.jorgecamarena.moneygoalapp.presentation.ui.feature.addExpense

import cafe.adriel.voyager.core.model.coroutineScope
import com.jorgecamarena.moneygoalapp.data.domain.interactors.SaveExpenseUseCase
import com.jorgecamarena.moneygoalapp.entity.Expense
import com.jorgecamarena.moneygoalapp.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock


class HomeViewModel (
    val saveExpenseUseCase: SaveExpenseUseCase
): BaseViewModel<HomeViewContract.Event, HomeViewContract.State, HomeViewContract.Effect>() {
    override fun createInitialState(): HomeViewContract.State =
        HomeViewContract.State(concept = "", amount = 0F)

    override fun handleEvent(event: HomeViewContract.Event) {
        when(event) {
            HomeViewContract.Event.OnSaveExpense -> saveExpense()
            is HomeViewContract.Event.OnUpdateAmount -> updateAmount(event.amount)
            is HomeViewContract.Event.OnUpdateConcept -> updateConcept(event.concept)
        }
    }

    private fun saveExpense() {
        coroutineScope.launch {
            with(uiState.value) {
                saveExpenseUseCase(Expense(null, concept, amount, Clock.System.now().toEpochMilliseconds()))
                    .onSuccess {
                        setState { copy( concept= "", amount = 0F) }
                        setEffect { HomeViewContract.Effect.ExpenseAdded }
                    }
                    .onFailure {
                        setState { copy(concept = "", amount= 0F) }
                    }
            }
        }
    }

    private fun updateConcept(concept: String) {
        println("Concept: $concept")
        coroutineScope.launch {
            setState { copy(concept = concept) }
        }
    }

    private fun updateAmount(amount: Float) {
        println("Amount: $amount")
        coroutineScope.launch {
            setState { copy(amount = amount) }
        }
    }



}