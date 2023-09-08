package com.jorgecamarena.moneygoalapp.presentation.ui.feature.addExpense

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import kotlinx.coroutines.flow.collectLatest
object HomeTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Rounded.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = "Home",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<HomeViewModel>()
        HomeMainScreen(viewModel)
    }
}

@Composable
fun HomeMainScreen(viewModel: HomeViewModel) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when(effect) {
                HomeViewContract.Effect.ExpenseAdded -> {
                    // PENDING: UI/UX feedback
                }
            }
        }
    }

    with(uiState) {
        AddExpenseView(
            concept = concept,
            amount = amount,
            onEvent = viewModel::setEvent
        )
    }

}

@Composable
fun AddExpenseView(
    concept: String,
    amount: Float,
    onEvent: (HomeViewContract.Event) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier.padding(vertical = 5.dp),
            value = concept,
            onValueChange = {
                onEvent(HomeViewContract.Event.OnUpdateConcept(it))
            },
            label = {
                Text("Concepto")
            },
            placeholder = {
                Text("Tacos")
            }
        )

        OutlinedTextField(
            modifier = Modifier.padding(vertical = 5.dp),
            value = if (amount > 0f) amount.toString() else "",
            onValueChange = {
                it.toFloatOrNull()?.let { value ->
                    onEvent(HomeViewContract.Event.OnUpdateAmount(value))
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            label = {
                Text("Cantidad")
            },
            placeholder = {
                Text("220.50")
            }
        )

        Button(
            modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(0.7F),
            onClick = {
                onEvent(HomeViewContract.Event.OnSaveExpense)
            },
            enabled = concept.isNotEmpty() && amount > 0F
        ) {
            Text(text = "Registrar Gasto".uppercase())
        }
    }
}