package com.jorgecamarena.moneygoalapp.presentation.ui.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.jorgecamarena.moneygoalapp.presentation.ui.feature.AppBottomBar
import com.jorgecamarena.moneygoalapp.presentation.ui.feature.addExpense.HomeTab
import com.jorgecamarena.moneygoalapp.presentation.ui.feature.expenseList.ExpenseListScreen

class HomeScreen: Screen {
    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {

        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(HomeTab, ExpenseListScreen)
                )
            }
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar =  {
                    AppBottomBar {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(ExpenseListScreen)
                    }
                }
            ) {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    CurrentTab()
                }
            }
        }
    }

    @Composable
    private fun TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current
        IconButton(
            onClick = { tabNavigator.current = tab },
        ) {
            Icon(painter = tab.options.icon!!, contentDescription = null)
        }
    }

}