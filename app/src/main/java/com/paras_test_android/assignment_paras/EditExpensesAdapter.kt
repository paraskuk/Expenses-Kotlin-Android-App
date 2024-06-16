package com.paras_test_android.assignment_paras

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView


class EditExpensesAdapter(private var expenses: List<ExpenseTable>) : RecyclerView.Adapter<EditExpensesAdapter.ExpenseViewHolder>() {

    /**
     * Function that creates the view holder on creation
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_edit_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    /**
     * Function that is binding the view holder to the data
     */
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.bind(expense)
    }

    override fun getItemCount(): Int {
        return expenses.size
    }

    /**
     * Method set to update the expenses list
     */
    fun updateExpenses(newExpenses: List<ExpenseTable>) {
        expenses = newExpenses
        notifyDataSetChanged()
        Log.d("EditExpensesAdapter", "Expenses updated: $expenses") // Log updated expenses
    }

    /**
     * Method to get empty expenses in the list
     */
    fun getEmptyExpenses(): List<ExpenseTable> {
        return expenses.filter { it.title.isBlank() && it.amount == 0.0 && it.date.isBlank() && it.category.isBlank() }
    }
    /**
     * Method to get non empty expenses in the list
     */
    fun getNonEmptyExpenses(): List<ExpenseTable> {
        return expenses.filter { it.title.isNotBlank() || it.amount != 0.0 || it.date.isNotBlank() || it.category.isNotBlank() }
    }

    fun getAllExpenses(): List<ExpenseTable> {
        return expenses
    }

    /**
     * Inner class for the expense view holder
     */
    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleEditText: EditText = itemView.findViewById(R.id.titleEditText)
        private val amountEditText: EditText = itemView.findViewById(R.id.amountEditText)
        private val dateEditText: EditText = itemView.findViewById(R.id.dateEditText)
        private val categoryEditText: EditText = itemView.findViewById(R.id.categoryEditText)

        fun bind(expense: ExpenseTable) {
            titleEditText.setText(expense.title)
            amountEditText.setText(expense.amount.toString())
            dateEditText.setText(expense.date)
            categoryEditText.setText(expense.category)
        }
    }
}




