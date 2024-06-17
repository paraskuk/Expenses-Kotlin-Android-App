package com.paras_test_android.assignment_paras

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.paras_test_android.assignment_paras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_add_expenses -> {
                    val intent = Intent(this, AddExpensesActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_show_expenses -> {
                    val intent = Intent(this, ShowExpensesActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_edit_expenses -> {
                    val intent = Intent(this, EditExpensesActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_show_statistics -> {
                    val intent = Intent(this, ShowStatisticsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_exit -> {
                    finishAffinity()
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        // Set up original menu buttons using View Binding
        binding.addExpensesButton.setOnClickListener {
            val intent = Intent(this, AddExpensesActivity::class.java)
            startActivity(intent)
        }

        binding.showExpensesButton.setOnClickListener {
            val intent = Intent(this, ShowExpensesActivity::class.java)
            startActivity(intent)
        }

        binding.editExpensesButton.setOnClickListener {
            val intent = Intent(this, EditExpensesActivity::class.java)
            startActivity(intent)
        }

        binding.showStatisticsButton.setOnClickListener {
            val intent = Intent(this, ShowStatisticsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
