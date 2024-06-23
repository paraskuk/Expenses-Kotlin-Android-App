package com.paras_test_android.assignment_paras

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches

import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.paras_test_android.assignment_paras.AppDatabase
import com.paras_test_android.assignment_paras.MainActivity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApplicationTestsInstrumented {
    //create instance of db
    private lateinit var db_1: AppDatabase

    //create instance of dao

    private lateinit var expenseDao: ExpenseDAO


    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Create an instance of the database before test
     */
    @Before
    fun createDb()  { runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        //create an instance of a database in memory
        db_1 = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        expenseDao = db_1.expenseDao()

        // Insert data to make sure the database is not empty
        val expense1 = ExpenseTable(title = "Cinema Ticket Other", amount = 10.0, date = "01/01/2024", category = "Relax")
        val expense2 = ExpenseTable(title = "Chips", amount = 20.0, date = "02/01/2024", category = "Groceries")
        val expense3 = ExpenseTable(title = "Burger", amount = 30.0, date = "03/01/2024", category = "Food")
        val expense4 = ExpenseTable(title = "Cinema Ticket Odeon", amount = 60.0, date = "04/01/2024", category = "Relax")

        expenseDao.insertExpenses(listOf(expense1, expense2, expense3, expense4))

    } }

    /**
     * populate db with data
     */
    @After
    fun populateDb() = runBlocking {
        // Insert data to populate the database after end of tests
        val expense1 = ExpenseTable(title = "Cinema Ticket Other", amount = 10.0, date = "01/01/2024", category = "Relax")
        val expense2 = ExpenseTable(title = "Chips", amount = 20.0, date = "02/01/2024", category = "Groceries")
        val expense3 = ExpenseTable(title = "Burger", amount = 30.0, date = "03/01/2024", category = "Food")
        val expense4 = ExpenseTable(title = "Cinema Ticket Odeon", amount = 60.0, date = "04/01/2024", category = "Relax")

        expenseDao.insertExpenses(listOf(expense1, expense2, expense3, expense4))
    }




    /**
     * Test the functionality of the show expenses
     */
    @Test
    fun testShowExpenses() {
        // MainActivity
        ActivityScenario.launch(MainActivity::class.java)

        // Click on the "Show Expenses On List" button
        onView(withId(R.id.showExpensesButton)).perform(click())

        // Verify RecyclerView is displayed
        onView(withId(R.id.expensesRecyclerView)).check(matches(isDisplayed()))
    }


    /**
     * Test the functionality of the  show statistics button
     */
    @Test
    fun testShowStatistics() {
        // Launch MainActivity
        ActivityScenario.launch(MainActivity::class.java)

        // Click on the "Show Statistics On List" button
        onView(withId(R.id.showStatisticsButton)).perform(click())

        // statistics are displayed
        onView(withId(R.id.mostFrequentTitleLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.mostFrequentCategoryLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.averageAmountPerCategoryLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.mostRecentExpenseLabel)).check(matches(isDisplayed()))
    }

    /**
     * close the db destroy the resource
     */
    @After
    fun closeDb() {
        db_1.close()
    }
}


