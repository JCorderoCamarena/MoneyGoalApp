package com.jorgecamarena.moneygoalapp.presentation.ui.feature.expenseList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FactCheck
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.jorgecamarena.moneygoalapp.entity.Expense
import com.jorgecamarena.moneygoalapp.presentation.model.ResourceUiState
import kotlin.math.roundToInt

object ExpenseListScreen : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Outlined.FactCheck)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Expenses",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<ExpenseListViewModel>()
        ExpenseListView(viewModel)
    }
}


@Composable
fun ExpenseListView(
    viewModel: ExpenseListViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    ManagementResourceUiState(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        resourceUiState = uiState.expenseList,
        successView = { favorites ->
            ExpenseList(
                expenses = favorites,
                onDeleteExpense = { expenseId ->
                    viewModel.setEvent(
                        ExpenseListViewContract.Event.OnDeleteExpense(
                            expenseId
                        )
                    )
                }
            )
        },
        onTryAgain = {  },
        onCheckAgain = { },
    )
}

@Composable
fun ExpenseList(
    expenses: List<Expense>,
    onDeleteExpense: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.9f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(expenses) { expense ->
                Card(
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(0.85F)
                ) {
                    Row(
                        modifier = Modifier.padding(all = 10.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(0.7f)
                        ) {
                            Text(text = expense.concept, fontWeight = FontWeight.Bold)
                            Text("$${expense.amount}")
                        }

                        IconButton(
                            onClick = {
                                expense.id?.let {
                                    onDeleteExpense(expense.id)
                                }
                            }
                        ) {
                            Icon(Icons.Outlined.Delete, contentDescription = null)
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier.weight(0.2f),
            horizontalArrangement = Arrangement.spacedBy(25.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier,
                text = "TOTAL",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier,
                text = "$${expenses.sumOf { it.amount.toDouble() }.roundToDecimals(3)} ",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun <T> ManagementResourceUiState(
    modifier: Modifier = Modifier,
    resourceUiState: ResourceUiState<T>,
    successView: @Composable (data: T) -> Unit,
    loadingView: @Composable () -> Unit = { Loading() },
    onTryAgain: () -> Unit,
    msgTryAgain: String = "No data to show",
    onCheckAgain: () -> Unit,
    msgCheckAgain: String = "An error has occurred"
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        when (resourceUiState) {
            is ResourceUiState.Empty -> Empty(modifier = modifier, onCheckAgain = onCheckAgain, msg = msgCheckAgain)
            is ResourceUiState.Error -> Error(modifier = modifier, onTryAgain = onTryAgain, msg = msgTryAgain)
            ResourceUiState.Loading -> loadingView()
            is ResourceUiState.Success -> successView(resourceUiState.data)
            ResourceUiState.Idle -> Unit
        }
    }
}


@Composable
fun Empty(
    modifier: Modifier = Modifier,
    msg: String,
    onCheckAgain: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = msg,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedButton(
                onClick = onCheckAgain
            ) {
                Text(
                    text = "Check Again",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Composable
fun Error(
    modifier: Modifier = Modifier,
    msg: String,
    onTryAgain: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = msg,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedButton(
                onClick = onTryAgain
            ) {
                Text(
                    text = "Try Again",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

fun Double.roundToDecimals(decimals: Int): Float {
    var dotAt = 1
    repeat(decimals) { dotAt *= 10 }
    val roundedValue = (this * dotAt).roundToInt()
    return (roundedValue / dotAt) + (roundedValue % dotAt).toFloat() / dotAt
}


@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator()
    }
}