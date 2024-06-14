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
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApplicationTestsInstrumented {

    private lateinit var db_1: AppDatabase

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        //create an instance of a database in memory
        db_1 = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDb() {
        db_1.close()
    }

    @Test
    fun testShowExpenses() {
        // MainActivity
        ActivityScenario.launch(MainActivity::class.java)

        // Click on the "Show Expenses On List" button
        onView(withId(R.id.showExpensesButton)).perform(click())

        // Verify RecyclerView is displayed
        onView(withId(R.id.expensesRecyclerView)).check(matches(isDisplayed()))
    }



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
}


