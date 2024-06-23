package com.paras_test_android.assignment_paras

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.paras_test_android.assignment_paras.databinding.ActivityShowStatisticsBinding
import kotlinx.coroutines.*

class ShowStatisticsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowStatisticsBinding
    private val expenseDao by lazy { AppDatabase.getDatabase(this).expenseDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showStatisticsButton.setOnClickListener {
            Log.d("ShowStatisticsActivity", "Show Statistics button clicked")
            showStatistics()
        }

        // Set up the back button using BackButtonHelper
        BackButtonHelper.setupBackButton(this, R.id.backButtonShow, MainActivity::class.java)
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
                    binding.mostFrequentTitleValue.text = "${mostFrequentTitle.title} (${mostFrequentTitle.count} times)"
                    binding.mostFrequentCategoryValue.text = "${mostFrequentCategory.category} (${mostFrequentCategory.count} times)"
                    //binding.averageAmountPerCategoryValue.text = averageAmounts.joinToString("\n") { "${it.category}: ${it.averageAmount}" }
                    binding.averageAmountPerCategoryValue.text = averageAmounts.joinToString("\n") { "${it.category}: ${it.averageAmount.roundToTwoDecimalPlaces()}" }
                    //binding.mostRecentExpenseValue.text = "${mostRecentExpense.title} (${mostRecentExpense.amount} on ${mostRecentExpense.date})"
                    binding.mostRecentExpenseValue.text = "${mostRecentExpense.title} (${mostRecentExpense.amount.roundToTwoDecimalPlaces()} on ${mostRecentExpense.date})"
                    Toast.makeText(this@ShowStatisticsActivity, "Statistics updated", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("ShowStatisticsActivity", "Error fetching statistics", e)
                withContext(Dispatchers.Main) {
                    binding.mostFrequentTitleValue.text = ""
                    binding.mostFrequentCategoryValue.text = ""
                    binding.averageAmountPerCategoryValue.text = ""
                    binding.mostRecentExpenseValue.text = "Failed to load statistics. Please make sure you have added expenses."
                    Toast.makeText(this@ShowStatisticsActivity, "Failed to load statistics", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
