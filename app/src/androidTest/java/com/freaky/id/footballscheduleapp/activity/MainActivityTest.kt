package com.freaky.id.footballscheduleapp.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.freaky.id.footballscheduleapp.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBarBehaviour(){
        onView(withId(toolbar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("Football Schedule App"))

        onView(withId(spinner2))
            .check(matches(isDisplayed()))
        onView(withId(spinner2)).perform(click())
        onView(withText("Spanish La Liga")).perform(click())
        delay()

    }

    @Test
    fun testRecyclerViewBehaviour(){

        delay()
        onView(withId(match_recycler_last))
            .check(matches(isDisplayed()))
        delay()
        onView(withId(match_recycler_last)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        onView(withId(match_recycler_last)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        delay()

        onView(withId(txtTeamNameA)).check(matches(isDisplayed()))
        onView(withId(txtTeamNameB)).check(matches(isDisplayed()))
        onView(withId(ivHome)).check(matches(isDisplayed()))
        onView(withId(ivAway)).check(matches(isDisplayed()))
    }

    @Test
    fun testFavoriteBehaviour(){

        delay()
        onView(withId(match_recycler_last))
            .check(matches(isDisplayed()))
        delay()
        onView(withId(match_recycler_last)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(withId(match_recycler_last)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        delay()

        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to Favorite"))
            .check(matches(isDisplayed()))

        pressBack()


        delay()
        onView(withId(match_recycler_last))
            .check(matches(isDisplayed()))
        delay()
        onView(withId(match_recycler_last)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(withId(match_recycler_last)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        delay()

        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed to Favorite"))
            .check(matches(isDisplayed()))



    }



    private fun delay(){
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }


}