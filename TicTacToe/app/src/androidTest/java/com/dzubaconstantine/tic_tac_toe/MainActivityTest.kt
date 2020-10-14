package com.dzubaconstantine.tic_tac_toe

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// https://github.com/android/testing-samples/tree/master/ui/espresso/BasicSample
// https://www.vogella.com/tutorials/AndroidTestingEspresso/article.html
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun uiTest() {
        onView(withId(R.id.startGameButton)).perform(click())
        for (i in 0 until 9)
            try {
                onView(withId(i)).perform(click())
            } catch (e: NoMatchingViewException) {
            }
        onView(withId(R.id.victoryMessageTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.victoryMessageTextView)).check(matches(withText("Победил X")))
    }
}
