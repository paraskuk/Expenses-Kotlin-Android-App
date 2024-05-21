package com.paras_test_android.assignment_paras

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Class adding expenses activity
 */
class AddExpensesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        val titleInput = findViewById<EditText>(R.id.titleInput)
        val amountInput = findViewById<EditText>(R.id.amountInput)
        val dateInput = findViewById<EditText>(R.id.dateInput)
        val categoryInput = findViewById<EditText>(R.id.categoryInput)
        val submitButton = findViewById<Button>(R.id.submitExpenseButton)

        submitButton.setOnClickListener {
            val title = titleInput.text.toString()
            val amount = amountInput.text.toString().toDouble()
            val date = dateInput.text.toString()
            val category = categoryInput.text.toString()

            val expense = ExpenseTable(title = title, amount = amount, date = date, category = category)

            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getDatabase(application).expenseDao().insertExpense(expense)

                launch(Dispatchers.Main) {
                    //return to main menu after adding expenses
                    navigateToMainMenu()
                }
            }

        }
    }

    /**
     * Function used to navigate to the main menu
     */
    private fun navigateToMainMenu() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK // Clears previous activities except for the new main
        startActivity(intent)
        finish()
    }

}
