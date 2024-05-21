package com.paras_test_android.assignment_paras
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the expenses table
 * DAO to me looks like the DTO objects in Java
 */
@Dao
interface ExpenseDAO {
    @Insert
    suspend fun insertExpense(expense: ExpenseTable)

    //select statement for viewing all expenses
    @Query("SELECT * FROM expenses")
    suspend fun getAllExpenses(): List<ExpenseTable>

    @Update
    suspend fun updateExpenses(expenses: List<ExpenseTable>)

}
