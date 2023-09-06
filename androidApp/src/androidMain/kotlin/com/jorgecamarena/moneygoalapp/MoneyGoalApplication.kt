package com.jorgecamarena.moneygoalapp

import MainView
import android.app.Application
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.jorgecamarena.moneygoalapp.data.di.initKoin
import org.koin.android.ext.koin.androidContext

class MoneyGoalApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MoneyGoalApplication)
        }
    }
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainView()
        }
    }
}