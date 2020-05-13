package com.example.djackad340;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;

import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class GalleryIntentTest {
    private static final String TAG = MainActivityIntentTest.class.getSimpleName();
    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);


    @Test
    public void checkImageInput() {

        // result to return from gallery
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_Uri, "img");
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, intent);

        // tells Espresso to respond with the ActivityResult
        intending(toPackage("com.google.android.apps.photos")).respondWith(result);

        // check an intent was sent to photos package
        onView(withId(R.id.profilePicBtn)).perform(click());
        intended(toPackage("com.google.android.apps.photos"));
    }

    @Test
    public void checkImageInputNull() {

        // result to return from gallery
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_Uri, "img");
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_CANCELED, intent);

        // tells Espresso to respond with the ActivityResult
        intending(toPackage("com.google.android.apps.photos")).respondWith(result);

        // check an intent was sent to photos package
        onView(withId(R.id.profilePicBtn)).perform(click());
        intended(toPackage("com.google.android.apps.photos"));
    }
}
