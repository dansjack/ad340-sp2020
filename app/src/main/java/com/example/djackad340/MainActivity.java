package com.example.djackad340;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText emailText;
    private EditText occupationText;
    private EditText locationText;
    private Button dobBtn;
    private TextView ageText;
    private EditText descText;
    private TextView errorText;
    private int yearsOfAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameText = findViewById(R.id.firstNameText);
        lastNameText = findViewById(R.id.lastNameText);
        emailText = findViewById(R.id.emailText);
        occupationText = findViewById(R.id.occupationText);
        locationText = findViewById(R.id.locationText);
        dobBtn = findViewById(R.id.dobBtn);
        ageText = findViewById(R.id.ageText);
        descText = findViewById(R.id.descText);
        errorText = findViewById(R.id.errorText);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_AGE, ageText.getText().toString());
        outState.putString(Constants.KEY_DOB, dobBtn.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ageText.setText( (String) savedInstanceState.get(Constants.KEY_AGE));
        dobBtn.setText( (String) savedInstanceState.get(Constants.KEY_DOB));
        try {
            yearsOfAge = Integer.parseInt(
                    (String) Objects.requireNonNull(savedInstanceState.get(Constants.KEY_AGE)));
        } catch(NumberFormatException exception){ // handle your exception
            yearsOfAge = 0;
        }
    }

    public void getFormSuccessActivity(View view) {
        if (isFormValid()) {
            Intent formSuccessIntent = new Intent(this, ProfileActivity.class);
            formSuccessIntent.putExtra(Constants.KEY_FNAME, firstNameText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_LNAME, lastNameText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_EMAIL, emailText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_OCC, occupationText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_LOC, locationText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_AGE, ageText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_DESC, descText.getText().toString());
            startActivity(formSuccessIntent);
        }
    }

    private boolean isFormValid() {
        boolean isValid = true;
        if (firstNameText.getText().toString().isEmpty() ||
                (lastNameText.getText().toString().isEmpty())) { // user didn't enter a full name
            isValid = false;
            errorText.setText(R.string.err_enter_name);
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                emailText.getText().toString().trim()).matches()) { // user didn't enter a valid password
            isValid = false;
            errorText.setText(R.string.err_enter_email);
        } else if (occupationText.getText().toString().isEmpty()) { // user didn't entered a occupation
            isValid = false;
            errorText.setText(R.string.err_enter_occ);
        } else if (locationText.getText().toString().isEmpty()) { // user didn't entered a location
            isValid = false;
            errorText.setText(R.string.err_enter_loc);
        } else if (yearsOfAge < 18) { // user is under 18 or over
            isValid = false;
            errorText.setText(R.string.err_enter_dob);
        } else if (descText.getText().toString().isEmpty()) { // user didn't entered any description
            isValid = false;
            errorText.setText(R.string.err_enter_desc);
        } else {
            errorText.setText("");
        }
        return isValid;
    }

    public void getDatePicker(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), getString(R.string.date_picker_tag));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(c.getTime());
        Period dateDiff = Period.between(LocalDate.parse(currentDate), java.time.LocalDate.now());
        String currentDateString = " " + DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        yearsOfAge = dateDiff.getYears();
        if (yearsOfAge < 1) yearsOfAge = 0;

        dobBtn.setText(currentDateString);
        ageText.setText(String.valueOf(yearsOfAge));
    }
}
