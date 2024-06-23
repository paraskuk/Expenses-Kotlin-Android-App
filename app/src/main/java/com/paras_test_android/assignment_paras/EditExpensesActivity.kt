package com.paras_test_android.assignment_paras

import android.content.Intent
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
            //save button
            val saveButton = findViewById<Button>(R.id.saveButton)
            saveButton.setOnClickListener {
                saveExpenses()

            }

             //here we add the back button to have better navigation
            BackButtonHelper.setupBackButton(this, R.id.backButtonEdit, MainActivity::class.java)

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

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveExpenses() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val expenseDao = AppDatabase.getDatabase(application).expenseDao()

                // Get all expenses
                val allExpenses = expensesAdapter.getAllExpenses()

                // Log the expenses for debugging
                Log.d("EditExpensesActivity", "All expenses: $allExpenses")

                // Identify empty and non-empty expenses to erase the rights ones later
                val (emptyExpenses, nonEmptyExpenses) = allExpenses.partition { expense ->
                    Log.d("EditExpensesActivity", "Evaluating expense: $expense")
                    expense.title.trim().isBlank() && expense.amount == 0.0 && expense.date.trim().isBlank() && expense.category.trim().isBlank()
                }

                // Delete empty expenses
                if (emptyExpenses.isNotEmpty()) {
                    Log.d("EditExpensesActivity", "Deleting empty expenses: $emptyExpenses")
                    expenseDao.deleteExpenses(emptyExpenses)
                }

                // Update non-empty expenses
                if (nonEmptyExpenses.isNotEmpty()) {
                    Log.d("EditExpensesActivity", "Updating non-empty expenses: $nonEmptyExpenses")
                    expenseDao.updateExpenses(nonEmptyExpenses)
                }

                // Update the adapter
                withContext(Dispatchers.Main) {
                    val updatedExpenses = nonEmptyExpenses.toMutableList()
                    expensesAdapter.updateExpenses(updatedExpenses)
                }

                // Close the activity
                withContext(Dispatchers.Main) {
                    finish()
                }

            } catch (e: Exception) {
                Log.e("EditExpensesActivity", "Error saving expenses: ${e.message}")
            }
        }
    }


}



