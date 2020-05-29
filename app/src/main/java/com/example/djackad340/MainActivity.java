package com.example.djackad340;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.djackad340.utils.DatePickerFragment;

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
    private int yearsOfAge;
    private Uri imageUri;
    private ImageView profilePicThumb;

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
        profilePicThumb = findViewById(R.id.profilePicThumb);
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
            Intent formSuccessIntent = new Intent(this, TabbedActivity.class);
            formSuccessIntent.putExtra(Constants.KEY_FNAME, firstNameText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_LNAME, lastNameText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_EMAIL, emailText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_OCC, occupationText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_LOC, locationText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_AGE, String.valueOf(yearsOfAge));
            formSuccessIntent.putExtra(Constants.KEY_DESC, descText.getText().toString());
            formSuccessIntent.putExtra(Constants.KEY_Uri, imageUri);

            startActivity(formSuccessIntent);
        }
    }

    private boolean isFormValid() {
        boolean isValid = true;
        if (firstNameText.getText().toString().isEmpty()) { // user didn't enter a first name
            firstNameText.setError(getString(R.string.err_enter_name));
            isValid = false;
        } else {
            firstNameText.setError(null);
        }

        if (lastNameText.getText().toString().isEmpty()) { // user didn't enter a last name
            lastNameText.setError(getString(R.string.err_enter_name));
            isValid = false;
        } else {
            lastNameText.setError(null);
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                emailText.getText().toString().trim()).matches()) { // user didn't enter a valid password
            emailText.setError(getString(R.string.err_enter_email));
            isValid = false;
        } else {
            emailText.setError(null);
        }

        if (occupationText.getText().toString().isEmpty()) { // user didn't entered a occupation
            occupationText.setError(getString(R.string.err_enter_occ));
            isValid = false;
        } else {
            occupationText.setError(null);
        }

        if (locationText.getText().toString().isEmpty()) { // user didn't entered a location
            locationText.setError(getString(R.string.err_enter_loc));
            isValid = false;
        } else {
            occupationText.setError(null);
        }

        if (yearsOfAge < 18) { // user is under 18 or over
            ageText.setText(R.string.err_enter_dob_young);
            isValid = false;
        } else {
            ageText.setError(null);
        }

        if (yearsOfAge > 99) { // user is improbably old
            ageText.setText(R.string.err_enter_dob_invalid);
            isValid = false;
        } else {
            ageText.setError(null);
        }

        if (descText.getText().toString().isEmpty()) { // user didn't entered any description
            descText.setError(getString(R.string.err_enter_desc));
            isValid = false;
        } else {
            descText.setError(null);
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
    }

    public void getImageFromPhone(View view) {
        Intent gallery = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, Constants.GET_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constants.GET_IMAGE){
            imageUri = data.getData();
            profilePicThumb.setImageURI(imageUri);
        }
    }
}
