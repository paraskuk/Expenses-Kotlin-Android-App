package com.paras_test_android.assignment_paras
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the expenses table
 * DAO to me look like the DTO objects in Java
 */
@Dao
interface ExpenseDAO {
    @Insert
    suspend fun insertExpense(expense: ExpenseTable)

    //select statement for viewing all xpenses
    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): List<ExpenseTable>
}
