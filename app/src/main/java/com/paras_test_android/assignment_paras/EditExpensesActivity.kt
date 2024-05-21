package com.paras_test_android.assignment_paras

import android.os.Bundle
import android.util.Log
import android.widget.Button
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
            expensesRecyclerView.adapter = expensesAdapter
            expensesRecyclerView.layoutManager = LinearLayoutManager(this)

            loadExpenses()

            val saveButton = findViewById<Button>(R.id.saveButton)
            saveButton.setOnClickListener {
                saveExpenses()
            }
        } catch (e: Exception) {
            Log.e("EditExpensesActivity", "Error initializing activity in the app", e)
        }
    }

    /**
     * loads expenses using Coroutines
     */
    private fun loadExpenses() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val expenses = AppDatabase.getDatabase(application).expenseDao().getAllExpenses()
                withContext(Dispatchers.Main) {
                    expensesAdapter.updateExpenses(expenses)
                }
            } catch (e: Exception) {
                Log.e("EditExpensesActivity", "Error loading expenses in the app", e)
            }
        }
    }

    /**
     * saves expenses using Coroutines
     */
    private fun saveExpenses() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                AppDatabase.getDatabase(application).expenseDao().updateExpenses(expensesAdapter.getExpenses())
                finish()
            } catch (e: Exception) {
                Log.e("EditExpensesActivity", "Error saving expenses in the app", e)
            }
        }
    }
}
