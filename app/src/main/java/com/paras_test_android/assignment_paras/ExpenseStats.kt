package com.paras_test_android.assignment_paras

/**
 * Data classes for expense statistics
 */

/**
 * Data class for expense title frequency tracking
 */
data class ExpenseTitleFrequency(val title: String, val count: Int)

/**
 * Data class for expense category frequency tracking
 */
data class ExpenseCategoryFrequency(val category: String, val count: Int)
/**
 * Data class for average amount of expenses per category
 */
data class CategoryAverageAmount(val category: String, val averageAmount: Double)
