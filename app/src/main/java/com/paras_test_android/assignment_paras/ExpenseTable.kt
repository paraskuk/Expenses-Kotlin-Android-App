package com.paras_test_android.assignment_paras
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Data class for Expense Table
 */
@Entity(tableName = "expenses")
data class ExpenseTable(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String,
    var amount: Double,
    var date: String,

    var category: String
)
