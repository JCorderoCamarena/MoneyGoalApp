package com.jorgecamarena.moneygoalapp.presentation.ui.addExpense

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeMainScreen(viewModel: HomeViewModel) {

    val uiState by viewModel.uiState.collectAsState()

    var concept by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf(0F) }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when(effect) {
                HomeViewContract.Effect.ExpenseAdded -> TODO()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(10.dp))

//        AnimatedVisibility(uiState.expenses.isNotEmpty()) {
//            LazyColumn {
//                items(uiState.expenses) {
//                    Row {
//                        Text(text = it.concept)
//                        Text(text = "${it.amount}")
//                    }
//                }
//            }
//        }

        TextField(
            value = concept,
            onValueChange = {
                concept = it
            }
        )

        TextField(
            value = amount.toString(),
            onValueChange = {
                amount = it.toFloat()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )

        Button(
            onClick = {
                viewModel.setEvent(HomeViewContract.Event.OnSaveExpense(concept, amount))
            },
            enabled = concept.isNotEmpty() && amount > 0F
        ) {
            Text(text = "Save Expense")
        }
    }

}