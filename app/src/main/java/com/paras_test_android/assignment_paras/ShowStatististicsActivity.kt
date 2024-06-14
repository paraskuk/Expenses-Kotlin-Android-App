package com.paras_test_android.assignment_paras

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class ShowStatisticsActivity : AppCompatActivity() {

    private lateinit var showStatisticsButton: Button
    private lateinit var mostFrequentTitleValue: TextView
    private lateinit var mostFrequentCategoryValue: TextView
    private lateinit var averageAmountPerCategoryValue: TextView
    private lateinit var mostRecentExpenseValue: TextView
    private val expenseDao by lazy { AppDatabase.getDatabase(this).expenseDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_statistics)

        showStatisticsButton = findViewById(R.id.showStatisticsButton)
        mostFrequentTitleValue = findViewById(R.id.mostFrequentTitleValue)
        mostFrequentCategoryValue = findViewById(R.id.mostFrequentCategoryValue)
        averageAmountPerCategoryValue = findViewById(R.id.averageAmountPerCategoryValue)
        mostRecentExpenseValue = findViewById(R.id.mostRecentExpenseValue)

        showStatisticsButton.setOnClickListener {
            Log.d("ShowStatisticsActivity", "Show Statistics button clicked")
            showStatistics()
        }
    }

    private fun showStatistics() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val mostFrequentTitle = expenseDao.getMostFrequentTitle()
                val mostFrequentCategory = expenseDao.getMostFrequentCategory()
                val averageAmounts = expenseDao.getAverageAmountPerCategory()
                val mostRecentExpense = expenseDao.getMostRecentExpense()

                Log.d("ShowStatisticsActivity", "Most Frequent Title: $mostFrequentTitle")
                Log.d("ShowStatisticsActivity", "Most Frequent Category: $mostFrequentCategory")
                Log.d("ShowStatisticsActivity", "Average Amounts: $averageAmounts")
                Log.d("ShowStatisticsActivity", "Most Recent Expense: $mostRecentExpense")

                withContext(Dispatchers.Main) {
                    mostFrequentTitleValue.text = "${mostFrequentTitle.title} (${mostFrequentTitle.count} times)"
                    mostFrequentCategoryValue.text = "${mostFrequentCategory.category} (${mostFrequentCategory.count} times)"
                    averageAmountPerCategoryValue.text = averageAmounts.joinToString("\n") { "${it.category}: ${it.averageAmount}" }
                    mostRecentExpenseValue.text = "${mostRecentExpense.title} (${mostRecentExpense.amount} on ${mostRecentExpense.date})"
                    Toast.makeText(this@ShowStatisticsActivity, "Statistics updated", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("ShowStatisticsActivity", "Error fetching statistics", e)
                withContext(Dispatchers.Main) {
                    mostFrequentTitleValue.text = ""
                    mostFrequentCategoryValue.text = ""
                    averageAmountPerCategoryValue.text = ""
                    mostRecentExpenseValue.text = "Failed to load statistics. Please make sure you have added expenses."
                    Toast.makeText(this@ShowStatisticsActivity, "Failed to load statistics", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
