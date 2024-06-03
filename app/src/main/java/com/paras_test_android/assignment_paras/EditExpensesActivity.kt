package com.paras_test_android.assignment_paras

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Class that edits expenses activity
 */


class EditExpensesActivity : AppCompatActivity() {
    private lateinit var expensesAdapter: EditExpensesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_expenses)

        try {
            val expensesRecyclerView = findViewById<RecyclerView>(R.id.editExpensesRecyclerView)
            expensesAdapter = EditExpensesAdapter(listOf())

            expensesRecyclerView.layoutManager = LinearLayoutManager(this)
            expensesRecyclerView.adapter = expensesAdapter

            loadExpenses()

            val saveButton = findViewById<Button>(R.id.saveButton)
            saveButton.setOnClickListener {
                saveExpenses()
            }
        } catch (e: Exception) {
            Log.e("EditExpensesActivity", "Error while initializing activity in the app", e)
        }
    }

    private fun loadExpenses() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val expenses = AppDatabase.getDatabase(application).expenseDao().getAllExpenses()
                Log.d("EditExpensesActivity", "Expenses loaded: $expenses") // Log expenses

                withContext(Dispatchers.Main) {
                    if (expenses.isNotEmpty()) {
                        expensesAdapter.updateExpenses(expenses)
                        Log.d("EditExpensesActivity", "Expenses updated in adapter")
                    } else {
                        Log.d("EditExpensesActivity", "No expense to show")
                    }
                }
            } catch (e: Exception) {
                Log.e("EditExpensesActivity", "Error loading expenses in the app", e)
            }
        }
    }

    private fun saveExpenses() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val expenseDao = AppDatabase.getDatabase(application).expenseDao()

                // Delete expenses with all empty fields
                val emptyExpenses = expensesAdapter.getEmptyExpenses()
                if (emptyExpenses.isNotEmpty()) {
                    Log.d("EditExpensesActivity", "Deleting empty expenses: $emptyExpenses")
                    expenseDao.deleteExpenses(emptyExpenses)
                }

                // Update non-empty expenses
                val nonEmptyExpenses = expensesAdapter.getNonEmptyExpenses()
                if (nonEmptyExpenses.isNotEmpty()) {
                    Log.d("EditExpensesActivity", "Updating non-empty expenses: $nonEmptyExpenses")
                    expenseDao.updateExpenses(nonEmptyExpenses)
                }

                finish()
            } catch (e: Exception) {
                Log.e("EditExpensesActivity", "Error saving expenses in the app", e)
            }
        }
    }
}

