package com.paras_test_android.assignment_paras

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
/**
 *Class that Shows expenses activity
 */
class ShowExpensesActivity : AppCompatActivity() {
    private lateinit var expensesAdapter: ExpensesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_expenses_activity)

        val expensesRecyclerView = findViewById<RecyclerView>(R.id.expensesRecyclerView)
        expensesAdapter = ExpensesAdapter(listOf())
        expensesRecyclerView.adapter = expensesAdapter
        expensesRecyclerView.layoutManager = LinearLayoutManager(this)

        loadExpenses()

        // here we add the back button
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            Toast.makeText(this, "I am going back to Main Menu !", Toast.LENGTH_SHORT).show()

            // Navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Function that  handles back button to improve usability
     */

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    /**
     * Function that loads expenses using Coroutines
     */
    private fun loadExpenses() {
        CoroutineScope(Dispatchers.IO).launch {
            val expenses = AppDatabase.getDatabase(application).expenseDao().getAllExpenses()
            withContext(Dispatchers.Main) {
                expensesAdapter.updateExpenses(expenses)
            }
        }
    }
}
