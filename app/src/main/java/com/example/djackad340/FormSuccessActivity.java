package com.example.djackad340;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FormSuccessActivity extends AppCompatActivity {
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String occ = "";
    private String dob = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_success);
        Intent mainIntent = getIntent();
        Bundle bundle = mainIntent.getExtras();
        TextView successMsg = findViewById(R.id.successMsg);
        StringBuilder msg = new StringBuilder(getString(R.string.SUCCESS_MSG_START));

        if (bundle != null) {
            firstName = bundle.getString(Constants.KEY_FNAME);
            lastName = bundle.getString(Constants.KEY_FNAME);
            email = bundle.getString(Constants.KEY_EMAIL);
            occ = bundle.getString(Constants.KEY_OCC);
            dob = bundle.getString(Constants.KEY_DOB);
        }
        msg.append(" ").append(occ).append(getString(R.string.SUCCESS_MSG_END));
        successMsg.setText(msg);
    }

    public void returnToSignUp(View view) {
        setResult(Constants.CODE_NEW_SIGNUP);
        finish();
    }


}
