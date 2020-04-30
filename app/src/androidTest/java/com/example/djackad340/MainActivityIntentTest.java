package com.example.djackad340;

import android.widget.DatePicker;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class MainActivityIntentTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);

    @Test
    public void verifyMessageSentToMessageActivity() throws InterruptedException {
        Thread.sleep(250);

        onView(withId(R.id.firstNameText)) // Enter First Name
                .perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)) // Enter Last Name
                .perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.usernameText)) // Enter Username
                .perform(typeText(Constants.TEST_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.dobBtn)).perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.submitBtn)).perform(click());
        intended(allOf(
                hasComponent(hasShortClassName(".FormSuccessActivity")),
                toPackage("com.example.djackad340"),
                hasExtra(Constants.KEY_FNAME, Constants.TEST_FNAME)));

        onView(withId(R.id.goBackBtn)).perform(click());
    }

    @Test
    public void verifyFormEmptyOnBack() {
        onView(withId(R.id.firstNameText)) // Enter First Name
                .perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)) // Enter Last Name
                .perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.usernameText)) // Enter Username
                .perform(typeText(Constants.TEST_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.dobBtn)).perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.goBackBtn)).perform(click());
        onView(withId(R.id.firstNameText)).check(matches(withText(Constants.EMPTY_STRING)));
        onView(withId(R.id.lastNameText)).check(matches(withText(Constants.EMPTY_STRING)));
        onView(withId(R.id.emailText)).check(matches(withText(Constants.EMPTY_STRING)));
        onView(withId(R.id.dobBtn)).check(matches(withText(Constants.EMPTY_STRING)));
        onView(withId(R.id.ageText)).check(matches(withText(Constants.EMPTY_STRING)));

    }


}
