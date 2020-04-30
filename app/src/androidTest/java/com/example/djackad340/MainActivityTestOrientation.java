package com.example.djackad340;

import android.os.RemoteException;
import android.widget.DatePicker;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class MainActivityTestOrientation {
    Calendar c = Calendar.getInstance();
    int currentYear = c.get(Calendar.YEAR);
    int currentMonth = c.get(Calendar.MONTH);
    int currentDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void noFailOnOrientationChange() throws RemoteException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationLeft();
        onView(withId(R.id.ageText))
                .perform(ViewActions.scrollTo())
                .check(matches(withText(Constants.EMPTY_STRING))); // Age

        device.setOrientationNatural();
        onView(withId(R.id.ageText)).check(matches(withText(Constants.EMPTY_STRING))); // Age
    }

    @Test
    public void dataPersistsOnOrientationChange() throws RemoteException {
        onView(withId(R.id.dobBtn)).perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());

        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationLeft();
        device.setOrientationNatural();
        onView(withId(R.id.ageText)).check(matches(withText(Constants.TEST_AGE))); // Age
    }


}