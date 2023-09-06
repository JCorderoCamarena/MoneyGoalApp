package com.jorgecamarena.moneygoalapp.entity

data class Expense(
    val id: Long? = null,
    val concept: String,
    val amount: Float,
    val timestamp: Long
)