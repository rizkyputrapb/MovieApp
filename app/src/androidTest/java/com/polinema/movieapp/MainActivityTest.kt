package com.polinema.movieapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.android.material.tabs.TabLayout
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun movierecyclerViewScroll() {
        onView(withId(R.id.rvMovies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
    }

    @Test
    fun clickMovietoDetail() {
        onView(withId(R.id.rvMovies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.collapsingWithSub))
        onView(withId(R.id.detailDuration)).check(matches(withText("2h 16m")))
        onView(withId(R.id.detailRating)).check(matches(withText("R")))
    }

    @Test
    fun tvShowRecyclerview() {
        onView(withId(R.id.tabLayout)).perform(selectTabAtPosition(1))
        Thread.sleep(1000)
        onView(withId(R.id.rvTvShow))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
    }

    @Test
    fun clickTvShowtoDetail() {
        onView(withId(R.id.tabLayout)).perform(selectTabAtPosition(1))
        Thread.sleep(1000)
        onView(withId(R.id.rvTvShow))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.detailDuration)).check(matches(withText("42m")))
        onView(withId(R.id.detailRating)).check(matches(withText("TV-14")))
    }
    
    fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = allOf(
                isDisplayed(),
                isAssignableFrom(TabLayout::class.java)
            )

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }

//    inline fun <reified F : Fragment> launchFragmentScenario(
//        bundle: Bundle?, fragment: F, navController: NavController
//    ): FragmentScenario<F> {
//        return launchFragmentInContainer(bundle, R.style.Theme_AppCompat) {
//            fragment.also { fragment ->
//                fragment.viewLifecycleOwnerLiveData.observeForever { lifeCycleOwner ->
//                    if (lifeCycleOwner != null) {
//                        // The fragment’s view has just been created
//                        Navigation.setViewNavController(fragment.requireView(), navController)
//                    }
//                }
//            }
//        }
//    }
//
//    private fun launchMyFragmentScenario(bundle: Bundle?): FragmentScenario<DetailFragment>
//            = launchFragmentScenario(bundle, DetailFragment(), mockNavController)
}