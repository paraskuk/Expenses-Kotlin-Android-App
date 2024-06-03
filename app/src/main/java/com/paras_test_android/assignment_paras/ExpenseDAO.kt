package com.paras_test_android.assignment_paras
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
//import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the expenses table
 * DAO to me looks like the DTO objects in Java
 */
@Dao
interface ExpenseDAO {
//    @Insert
//    suspend fun insertExpense(expense: ExpenseTable)

    @Insert
    suspend fun insertExpense(expense: List<ExpenseTable>)

    //select statement for viewing all expenses
    @Query("SELECT * FROM expenses")
    suspend fun getAllExpenses(): List<ExpenseTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(expenses: List<ExpenseTable>)

    @Update
    suspend fun updateExpenses(expenses: List<ExpenseTable>)

    @Delete
    suspend fun deleteExpenses(expenses: List<ExpenseTable>)

    @Query("SELECT title, COUNT(title) as count FROM expenses GROUP BY title ORDER BY count DESC LIMIT 1")
    suspend fun getMostFrequentTitle(): ExpenseTitleFrequency

    @Query("SELECT category, COUNT(category) as count FROM expenses GROUP BY category ORDER BY count DESC LIMIT 1")
    suspend fun getMostFrequentCategory(): ExpenseCategoryFrequency

    @Query("SELECT category, AVG(amount) as averageAmount FROM expenses GROUP BY category")
    suspend fun getAverageAmountPerCategory(): List<CategoryAverageAmount>

    @Query("SELECT * FROM expenses ORDER BY date DESC LIMIT 1")
    suspend fun getMostRecentExpense(): ExpenseTable

}

