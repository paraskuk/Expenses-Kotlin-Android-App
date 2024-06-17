package com.paras_test_android.assignment_paras

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Application class for the app that populates the database with some data
 * This will allow the Teacher to see some data when they run the app right after
 * running the tests
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            initiateDatabase()
        }
    }

    /**
     * Function that I used before to populate the database with some data
     *
     */
    private suspend fun initiateDatabase() {
        val expenseDao = AppDatabase.getDatabase(this).expenseDao()

        // Insert data  will be same as in the tests
        val expense1 = ExpenseTable(title = "Cinema Ticket Other", amount = 10.0, date = "01/01/2024", category = "Relax")
        val expense2 = ExpenseTable(title = "Chips", amount = 20.0, date = "02/01/2024", category = "Groceries")
        val expense3 = ExpenseTable(title = "Burger", amount = 30.0, date = "03/01/2024", category = "Food")
        val expense4 = ExpenseTable(title = "Cinema Ticket Odeon", amount = 60.0, date = "04/01/2024", category = "Relax")

        expenseDao.insertExpenses(listOf(expense1, expense2, expense3, expense4))
    }
}