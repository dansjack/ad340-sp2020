package com.example.djackad340;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void hasHelloWorld() {
        onView(withId(R.id.helloWorld))
                .check(matches(withText(R.string.helloWorld)));
    }

    @Test
    public void hasNameDate() {
        onView(withId(R.id.nameDateView))
                .check(matches(withText(R.string.nameDate)));
    }

    @Test
    public void hasCorrectImage() {
        onView(withId(R.id.simpsons)) // check for correct image
                .check(matches(withContentDescription(R.string.simpsons_fine)));

        onView(withId(R.id.myButton)).perform(click()); // toggle button

        onView(withId(R.id.simpsons)) //check again for correct image
                .check(matches(withContentDescription(R.string.simpsons_hell)));

    }

}