package com.paras_test_android.assignment_paras
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Data class for Expense Table
 */
@Entity(tableName = "expenses")
data class ExpenseTable(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: Double,
    val date: String,

    val category: String
)
