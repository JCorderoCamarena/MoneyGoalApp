package com.jorgecamarena.moneygoalapp.presentation.ui.feature


import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.FactCheck
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AppBottomBar(
    actions: @Composable (RowScope.() -> Unit)
) {
    BottomAppBar(
        floatingActionButton =  {
            FloatingActionButton(
                onClick = { }
            ) {
                Icon(Icons.Filled.Add, null)
            }
        },
        actions = actions
    )
}