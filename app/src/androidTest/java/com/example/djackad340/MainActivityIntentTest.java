package com.example.djackad340;

import android.view.View;
import android.widget.DatePicker;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnHolderItem;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class MainActivityIntentTest {
    private static final String TAG = MainActivityIntentTest.class.getSimpleName();
    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);


    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    @Before
    public void init() {
        onView(withId(R.id.firstNameText)) // Enter First Name
                .perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)) // Enter Last Name
                .perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.occupationText)) // Enter Occupation
                .perform(typeText(Constants.TEST_OCCUPATION), closeSoftKeyboard());
        onView(withId(R.id.locationText)) // Enter Location
                .perform(typeText(Constants.TEST_LOCATION), closeSoftKeyboard());
        onView(withId(R.id.dobBtn))
                .perform(ViewActions.scrollTo())
                .perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.descText)) // Enter Description
                .perform(ViewActions.scrollTo())
                .perform(typeText(Constants.TEST_DESC), closeSoftKeyboard());
        onView(withId(R.id.submitBtn)).perform(click());
    }

    @Test
    public void verifyFormSentToProfileActivity() {
        intended(allOf(
                hasComponent(hasShortClassName(".TabbedActivity")),
                toPackage("com.example.djackad340"),
                hasExtra(Constants.KEY_FNAME, Constants.TEST_FNAME)));
    }

    @Test
    public void verifyFragments() {
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.settingsPlaceholder)).check(matches(withText(Constants.TEST_SETTINGS_PL)));
        onView(withId(R.id.view_pager)).perform(swipeRight());
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.settingsPlaceholder)).check(matches(withText(Constants.TEST_SETTINGS_PL)));
    }

    @Test
    public void hasFirstMatch() {
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withRecyclerView(R.id.recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Luanne"))));
        onView(withRecyclerView(R.id.recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("19, Arlen, TX"))));
    }

    @Test
    public void hasLastMatch() {
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.recycler_view)).perform(scrollToPosition(9));
        onView(withRecyclerView(R.id.recycler_view)
                .atPosition(9))
                .check(matches(hasDescendant(withText("Bill"))));
        onView(withRecyclerView(R.id.recycler_view).atPosition(9))
                .check(matches(hasDescendant(withText("45, Arlen, TX"))));
    }

}
