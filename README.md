# MoneyGoalApp
Multiplatform app to keep track of money you want to save monthly


Base code template downloaded from [here](https://github.com/JetBrains/compose-multiplatform-ios-android-template/#readme)

Java version required: 17
Compiled using: Android Studio Giraffe

### Database Schema

TABLE: Expenses
|Column   |Type   |
|---------|-------|
|concept  |TEXT   |
|amount   |REAL   |
|timestamp|INTEGER|

TABLE: ExpenseLimit
|Column   |Type   |
|---------|-------|
|amount   |REAL   |
|timestamp|INTEGER|