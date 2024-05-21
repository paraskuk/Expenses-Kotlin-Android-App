package com.paras_test_android.assignment_paras

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class EditExpensesAdapter(private var expenses: List<ExpenseTable>) :
    RecyclerView.Adapter<EditExpensesAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleEditText: EditText = view.findViewById(R.id.titleEditText)
        val amountEditText: EditText = view.findViewById(R.id.amountEditText)
        val dateEditText: EditText = view.findViewById(R.id.dateEditText)
        val categoryEditText: EditText = view.findViewById(R.id.categoryEditText)
    }

    private val editableExpenses = expenses.map { it.copy() }.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_edit_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = editableExpenses[position]
        holder.titleEditText.setText(expense.title)
        holder.amountEditText.setText(expense.amount.toString())
        holder.dateEditText.setText(expense.date)
        holder.categoryEditText.setText(expense.category)

        holder.titleEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                editableExpenses[position] = expense.copy(title = s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        holder.amountEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                editableExpenses[position] = expense.copy(amount = s.toString().toDoubleOrNull() ?: 0.0)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        holder.dateEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                editableExpenses[position] = expense.copy(date = s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        holder.categoryEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                editableExpenses[position] = expense.copy(category = s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun getItemCount(): Int {
        return editableExpenses.size
    }

    fun updateExpenses(newExpenses: List<ExpenseTable>) {
        editableExpenses.clear()
        editableExpenses.addAll(newExpenses.map { it.copy() })
        notifyDataSetChanged()
    }

    fun getExpenses(): List<ExpenseTable> {
        return editableExpenses
    }
}
