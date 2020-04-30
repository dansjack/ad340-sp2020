package com.example.djackad340;

import android.os.RemoteException;
import android.widget.DatePicker;

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
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    Calendar c = Calendar.getInstance();
    int currentYear = c.get(Calendar.YEAR);
    int currentMonth = c.get(Calendar.MONTH);
    int currentDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);


    @Test
    public void hasNameDate() {
        onView(withId(R.id.nameDateView)).check(matches(withText(R.string.nameDate)));
    }

    @Test
    public void hasCorrectFirstName() {
        onView(withId(R.id.firstNameText)) // Enter First Name
                .perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.firstNameText)).check(matches(withText(Constants.TEST_FNAME)));
    }

    @Test
    public void hasCorrectLastName() {
        onView(withId(R.id.lastNameText)) // Enter Last Name
                .perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)).check(matches(withText(Constants.TEST_LNAME)));
    }

    @Test
    public void hasCorrectEmail() { // Enter Email
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.emailText)).check(matches(withText(Constants.TEST_EMAIL)));
    }

    @Test
    public void hasCorrectUsername() throws InterruptedException {
        onView(withId(R.id.usernameText)) // Enter Username
                .perform(typeText(Constants.TEST_USERNAME), closeSoftKeyboard());

        Thread.sleep(250);

        onView(withId(R.id.usernameText))
                .check(matches(withText(Constants.TEST_USERNAME)));
    }

    @Test
    public void hasCorrectBirthday() {
        onView(withId(R.id.dobBtn)).perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                Constants.TEST_YEAR, 12, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.dobBtn)).check(matches(withText(Constants.TEST_DOB))); // Birthday
        onView(withId(R.id.ageText)).check(matches(withText("32"))); // Age
    }

    @Test
    public void hasCorrectBirthdayAlt() {
        if (currentDayOfMonth < 28) {
            onView(withId(R.id.dobBtn)).perform(click()); // Enter Birthday
            onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                    Constants.TEST_YEAR, currentMonth, currentDayOfMonth + 1));
            onView(withId(android.R.id.button1)).perform(click());

            onView(withId(R.id.ageText)).check(matches(withText(Integer.toString((currentYear - Constants.TEST_YEAR))))); // Age
        }
    }

    @Test
    public void dataPersistsOnOrientationChange() throws RemoteException {
        onView(withId(R.id.dobBtn)).perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());

        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationLeft();
        onView(withId(R.id.ageText)).check(matches(withText(Constants.TEST_AGE))); // Age
        device.setOrientationNatural();
        onView(withId(R.id.ageText)).check(matches(withText(Constants.TEST_AGE))); // Age
    }

    @Test
    public void hasNoFirstName() throws InterruptedException {
        onView(withId(R.id.lastNameText)).perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.usernameText)).perform(typeText(Constants.TEST_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.dobBtn)).perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());

        Thread.sleep(250);
        closeSoftKeyboard();

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.errorText)).check(matches(withText(R.string.err_enter_name)));
    }

    @Test
    public void hasNoLastName() throws InterruptedException {
        onView(withId(R.id.firstNameText)).perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.usernameText)).perform(typeText(Constants.TEST_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.dobBtn)).perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());

        Thread.sleep(250);
        closeSoftKeyboard();

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.errorText)).check(matches(withText(R.string.err_enter_name)));
    }

    @Test
    public void hasNoEmail() throws InterruptedException {
        onView(withId(R.id.firstNameText)).perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)).perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.usernameText)).perform(typeText(Constants.TEST_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.dobBtn)).perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());

        Thread.sleep(250);
        closeSoftKeyboard();

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.errorText)).check(matches(withText(R.string.err_enter_email)));
    }

    @Test
    public void hasNoUsername() throws InterruptedException {
        onView(withId(R.id.firstNameText)).perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)).perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.dobBtn)).perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());

        Thread.sleep(250);
        closeSoftKeyboard();

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.errorText)).check(matches(withText(R.string.err_enter_username)));
    }

    @Test
    public void hasNoBirthday() throws InterruptedException {
        onView(withId(R.id.firstNameText)).perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)).perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.usernameText)).perform(typeText(Constants.TEST_USERNAME), closeSoftKeyboard());

        Thread.sleep(250);
        closeSoftKeyboard();

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.errorText)).check(matches(withText(R.string.err_enter_dob)));
    }

    @Test
    public void hasInvalidBirthday() throws InterruptedException {
        onView(withId(R.id.dobBtn)).perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                currentYear + 10, 1, 31));
        onView(withId(android.R.id.button1)).perform(click());

        Thread.sleep(250);
        closeSoftKeyboard();

        onView(withId(R.id.ageText)).check(matches(withText(Constants.TEST_AGE_INVALID))); // Age
    }



    @Test
    public void noFailOnOrientationChange() throws RemoteException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationLeft();
        onView(withId(R.id.ageText)).check(matches(withText(Constants.EMPTY_STRING))); // Age
        device.setOrientationNatural();
        onView(withId(R.id.ageText)).check(matches(withText(Constants.EMPTY_STRING))); // Age
    }


}