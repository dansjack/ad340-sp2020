package com.example.djackad340;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void getFormSuccessActivity(View view) {
        final EditText nameText = findViewById(R.id.nameText);
        final EditText emailText = findViewById(R.id.emailText);
        final EditText usernameText = findViewById(R.id.usernameText);
        final EditText dobText = findViewById(R.id.dobText);

        Log.i(TAG, nameText.getText().toString());
        Log.i(TAG, emailText.getText().toString());
        Log.i(TAG, usernameText.getText().toString());
        Log.i(TAG, dobText.getText().toString());
    }
}
