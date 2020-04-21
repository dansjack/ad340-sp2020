package com.example.djackad340;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class FormSuccessActivity extends AppCompatActivity {
    private static final String TAG = FormSuccessActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_success);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String name = "";
        String email = "";
        String username = "";
        String dob = "";

        TextView successMsg = findViewById(R.id.successMsg);
        StringBuilder msg = new StringBuilder("Thanks for Signing Up, ");


        if (bundle != null && bundle.containsKey(Constants.KEY_NAME)) {
            name = bundle.getString(Constants.KEY_NAME);
            email = bundle.getString(Constants.KEY_EMAIL);
            username = bundle.getString(Constants.KEY_USERNAME);
            dob = bundle.getString(Constants.KEY_DOB);

            Log.i(TAG, name);
            Log.i(TAG, email);
            Log.i(TAG, username);
            Log.i(TAG, dob);

        } else {
            Log.i(TAG, "onCreate: Bundle is null");
        }
        msg.append(username).append("!");
        successMsg.setText(msg);
    }

    public void returnToSignUp(View view) {
        Intent intent = new Intent(FormSuccessActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
