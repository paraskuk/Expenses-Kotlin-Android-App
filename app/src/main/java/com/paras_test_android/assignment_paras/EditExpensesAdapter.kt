package com.paras_test_android.assignment_paras

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

/***
 * Adapter class for editing expenses
 */
class EditExpensesAdapter(private var expenses: List<ExpenseTable>) : RecyclerView.Adapter<EditExpensesAdapter.ExpenseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_edit_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.bind(expense)
    }

    override fun getItemCount(): Int {
        return expenses.size
    }

    fun updateExpenses(newExpenses: List<ExpenseTable>) {
        expenses = newExpenses
        notifyDataSetChanged()
    }

    fun getAllExpenses(): List<ExpenseTable> {
        return expenses
    }

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

            titleEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    expense.title = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            amountEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    expense.amount = s.toString().toDoubleOrNull() ?: 0.0
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            dateEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    expense.date = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            categoryEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    expense.category = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }
}
