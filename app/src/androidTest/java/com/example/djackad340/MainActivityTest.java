package com.example.djackad340;

import android.os.RemoteException;
import android.widget.DatePicker;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
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
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private Calendar c = Calendar.getInstance();
    private int currentYear = c.get(Calendar.YEAR);
    private int currentMonth = c.get(Calendar.MONTH);
    private int currentDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

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
    public void hasCorrectOccupation() {
        onView(withId(R.id.occupationText)) // Enter Occupation
                .perform(typeText(Constants.TEST_OCCUPATION), closeSoftKeyboard());
        onView(withId(R.id.occupationText))
                .check(matches(withText(Constants.TEST_OCCUPATION)));
    }

    @Test
    public void hasCorrectLocation() {
        onView(withId(R.id.locationText)) // Enter Location
                .perform(typeText(Constants.TEST_LOCATION), closeSoftKeyboard());
        onView(withId(R.id.locationText))
                .check(matches(withText(Constants.TEST_LOCATION)));
    }

    @Test
    public void hasCorrectBirthday() throws InterruptedException {
        Thread.sleep(2000);
        closeSoftKeyboard();
        onView(withId(R.id.dobBtn))
                .perform(ViewActions.scrollTo(), click()); // Enter Birthday
        Thread.sleep(2000);
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                Constants.TEST_YEAR, 12, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.dobBtn)).check(matches(withText(Constants.TEST_DOB))); // Birthday
    }

    @Test
    public void hasCorrectDescription() {
        onView(withId(R.id.descText)) // Enter Description
                .perform(ViewActions.scrollTo())
                .perform(typeText(Constants.TEST_DESC), closeSoftKeyboard());
        onView(withId(R.id.descText))
                .check(matches(withText(Constants.TEST_DESC)));
    }

    @Test
    public void dataPersistsOnOrientationChange() throws RemoteException {
        onView(withId(R.id.dobBtn))
                .perform(ViewActions.scrollTo())
                .perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                currentYear - 100, currentMonth, currentDayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitBtn)).perform(click());

        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationLeft();
        onView(withId(R.id.ageText))
                .perform(ViewActions.scrollTo())
                .check(matches(
                withText(Constants.TEST_DOBI_ERR)));
        device.setOrientationNatural();
        onView(withId(R.id.ageText)).check(matches(
                withText(Constants.TEST_DOBI_ERR)));
    }

    @Test
    public void hasNoFirstName() {
        onView(withId(R.id.lastNameText)).perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.firstNameText)).check(matches(
                ViewMatchers.hasErrorText(Constants.TEST_NAME_ERR)));
    }

    @Test
    public void hasNoLastName() {
        onView(withId(R.id.firstNameText)).perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.lastNameText)).check(matches(
                ViewMatchers.hasErrorText(Constants.TEST_NAME_ERR)));
    }

    @Test
    public void hasNoEmail() {
        onView(withId(R.id.firstNameText)).perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)).perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.emailText)).check(matches(
                ViewMatchers.hasErrorText(Constants.TEST_EMAIL_ERR)));
    }

    @Test
    public void hasNoOccupation() {
        onView(withId(R.id.firstNameText)).perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)).perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.occupationText)).check(matches(
                ViewMatchers.hasErrorText(Constants.TEST_OCC_ERR)));
    }

    @Test
    public void hasNoLocation() {
        onView(withId(R.id.firstNameText)).perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)).perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.occupationText)).perform(typeText(Constants.TEST_OCCUPATION), closeSoftKeyboard());

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.locationText)).check(matches(
                hasErrorText(Constants.TEST_LOC_ERR)));
    }

    @Test
    public void hasNoBirthday() {
        onView(withId(R.id.firstNameText)).perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)).perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.occupationText)).perform(typeText(Constants.TEST_OCCUPATION), closeSoftKeyboard());
        onView(withId(R.id.locationText)).perform(typeText(Constants.TEST_LOCATION), closeSoftKeyboard());

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.ageText)).check(matches(withText(Constants.TEST_DOBY_ERR)));
    }

    @Test
    public void hasNoDesc() {
        onView(withId(R.id.firstNameText)).perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)).perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.occupationText)).perform(typeText(Constants.TEST_OCCUPATION), closeSoftKeyboard());
        onView(withId(R.id.locationText)).perform(typeText(Constants.TEST_LOCATION), closeSoftKeyboard());
        onView(withId(R.id.dobBtn)).perform(ViewActions.scrollTo()).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                Constants.TEST_YEAR, 12, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.descText)).check(matches(
                ViewMatchers.hasErrorText(Constants.TEST_DESC_ERR)));
    }

    @Test
    public void ageTooLow() {
        onView(withId(R.id.dobBtn))
                .perform(ViewActions.scrollTo())
                .perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                currentYear + 10, currentMonth, currentDayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.ageText)).check(matches(withText(Constants.TEST_DOBY_ERR))); // Age
    }

    @Test
    public void ageTooHigh() {
        onView(withId(R.id.firstNameText)).perform(typeText(Constants.TEST_FNAME), closeSoftKeyboard());
        onView(withId(R.id.lastNameText)).perform(typeText(Constants.TEST_LNAME), closeSoftKeyboard());
        onView(withId(R.id.emailText)).perform(typeText(Constants.TEST_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.occupationText)).perform(typeText(Constants.TEST_OCCUPATION), closeSoftKeyboard());
        onView(withId(R.id.locationText)).perform(typeText(Constants.TEST_LOCATION), closeSoftKeyboard());
        onView(withId(R.id.dobBtn))
                .perform(ViewActions.scrollTo())
                .perform(click()); // Enter Birthday
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(
                currentYear - 100, currentMonth, currentDayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.descText)) // Enter Description
                .perform(ViewActions.scrollTo())
                .perform(typeText(Constants.TEST_DESC), closeSoftKeyboard());

        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.ageText)).check(matches(
                withText(Constants.TEST_DOBI_ERR)));
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