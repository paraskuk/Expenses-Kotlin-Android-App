package com.paras_test_android.assignment_paras

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.paras_test_android.assignment_paras.databinding.ActivityAddExpenseBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Activity for adding expenses.
 */
class AddExpensesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitExpenseButton.setOnClickListener {
            addExpense()
        }

        // Set up the back button using BackButtonHelper
        BackButtonHelper.setupBackButton(this, R.id.backButtonAdd, MainActivity::class.java)
    }

    private fun addExpense() {
        val title = binding.titleInput.text.toString()
        val amount = binding.amountInput.text.toString().toDoubleOrNull() ?: 0.0
        val date = binding.dateInput.text.toString()
        val category = binding.categoryInput.text.toString()

        if (title.isNotBlank() && date.isNotBlank() && category.isNotBlank()) {
            val expense = ExpenseTable(title = title, amount = amount, date = date, category = category)
            saveExpenseToDatabase(expense)
        } else {
            Log.d("AddExpensesActivity", "Invalid input: Title, Date, and Category must not be empty.")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveExpenseToDatabase(expense: ExpenseTable) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val expenseDao = AppDatabase.getDatabase(application).expenseDao()
                expenseDao.insertExpense(listOf(expense))
                Log.d("AddExpensesActivity", "Expense saved: $expense")
                finish()
            } catch (e: Exception) {
                Log.e("AddExpensesActivity", "Error saving expense", e)
            }
        }
    }
}
