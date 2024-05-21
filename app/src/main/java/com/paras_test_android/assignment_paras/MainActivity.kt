package com.paras_test_android.assignment_paras

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity()
//{
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//    }
//}
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //this adds the add button
        val addButton = findViewById<Button>(R.id.addExpensesButton)
        //and this adds the show button
        val showButton = findViewById<Button>(R.id.showExpensesButton)
        //and this adds the edit button for expenses
        val editButton = findViewById<Button>(R.id.editExpensesButton)

        val statisticsButton = findViewById<Button>(R.id.showStatisticsButton)

        //add clicklistener for add expenses button
        addButton.setOnClickListener {
            Toast.makeText(this, "Going to Add Expenses", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AddExpensesActivity::class.java)
            startActivity(intent)
        }
        //add clicklistener for show expenses button
        showButton.setOnClickListener {
            Toast.makeText(this, "Going to Show Expenses", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ShowExpensesActivity::class.java))
        }
        //add clicklistener for edit expenses button
        editButton.setOnClickListener {
            try {
                val intent = Intent(this, EditExpensesActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("MainActivity", "Error starting EditExpensesActivity", e)
            }
        }

        //add clicklistener for statistics  expenses button
        statisticsButton.setOnClickListener {
            Toast.makeText(this, "Going to Statistics on Expenses", Toast.LENGTH_SHORT).show()
        }
    }
}