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
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText emailText;
    private EditText usernameText;
    private Button dobBtn;
    private TextView ageText;
    private int yearsOfAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameText = findViewById(R.id.firstNameText);
        lastNameText = findViewById(R.id.lastNameText);
        emailText = findViewById(R.id.emailText);
        usernameText = findViewById(R.id.usernameText);
        dobBtn = findViewById(R.id.dobBtn);
        ageText = findViewById(R.id.ageText);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_AGE, ageText.getText().toString());
        outState.putString(Constants.KEY_DOB, dobBtn.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.KEY_AGE)) {
            ageText.setText(
                    (String) savedInstanceState.get(Constants.KEY_AGE));
            dobBtn.setText(
                    (String) savedInstanceState.get(Constants.KEY_DOB));
            try {
                yearsOfAge = Integer.parseInt(
                        (String) Objects.requireNonNull(savedInstanceState.get(Constants.KEY_AGE)));
            } catch(NumberFormatException exception){ // handle your exception
                Log.i(TAG, "onRestoreInstanceState: Exception success");
                yearsOfAge = 0;
            }

        }
    }

    public void getFormSuccessActivity(View view) {
        if (isFormValid()) {
            Intent intent = new Intent(MainActivity.this, FormSuccessActivity.class);
            intent.putExtra(Constants.KEY_FNAME, firstNameText.getText().toString());
            intent.putExtra(Constants.KEY_LNAME, lastNameText.getText().toString());
            intent.putExtra(Constants.KEY_EMAIL, emailText.getText().toString());
            intent.putExtra(Constants.KEY_USERNAME, usernameText.getText().toString());
            intent.putExtra(Constants.KEY_DOB, dobBtn.getText().toString());
            startActivity(intent);
        }
    }

    public boolean isFormValid() {
        boolean isValid = true;

        if (firstNameText.getText().toString().isEmpty() ||
                (lastNameText.getText().toString().isEmpty())) { // user didn't enter a name
            isValid = false;
            Toast.makeText(this, R.string.toast_enter_name, Toast.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                emailText.getText().toString().trim()).matches()) { // user didn't enter a valid password
            isValid = false;
            Toast.makeText(this, R.string.toast_enter_email, Toast.LENGTH_SHORT).show();
        } else if (usernameText.getText().toString().isEmpty()) { // user didn't entered a username
            isValid = false;
            Toast.makeText(this, R.string.toast_enter_username, Toast.LENGTH_SHORT).show();
        } else if (yearsOfAge < 18) { // user is under 18 or over
            isValid = false;
            Toast.makeText(this, R.string.toast_enter_dob, Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }

    public void getDatePicker(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        int currentDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        yearsOfAge = currentYear - year;
        if ((month > currentMonth) || (month == currentMonth && dayOfMonth > currentDayOfMonth)) {
            yearsOfAge--;
        }
        if (yearsOfAge < 1) yearsOfAge = 0;


        dobBtn.setText(currentDateString);
        ageText.setText(String.valueOf(yearsOfAge));
    }
}
