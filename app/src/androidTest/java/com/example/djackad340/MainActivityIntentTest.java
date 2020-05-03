package com.example.djackad340;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.DatePicker;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
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
import static androidx.test.espresso.intent.Intents.intending;
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
    public void verifyFormSentToProfileActivity() {
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
        intended(allOf(
                hasComponent(hasShortClassName(".ProfileActivity")),
                toPackage("com.example.djackad340"),
                hasExtra(Constants.KEY_FNAME, Constants.TEST_FNAME)));
    }

    @Test
    public void validateCameraInput() {

        // simulated image
        Bitmap img = BitmapFactory.decodeResource(ApplicationProvider.getApplicationContext().getResources(),
                R.drawable.bobby);

        // result to return from gallery
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_Uri, img);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, intent);

        // tells Espresso to respond with the ActivityResult
        intending(toPackage("com.google.android.apps.photos")).respondWith(result);

        // check an intent was sent to photos package
        onView(withId(R.id.profilePicBtn)).perform(click());
        intended(toPackage("com.google.android.apps.photos"));
    }
}
