package com.example.djackad340;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private TextView profName ;
    private TextView profAge;
    private TextView profOcc ;
    private TextView profDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profName = findViewById(R.id.profileName);
        profAge = findViewById(R.id.profileAge);
        profOcc = findViewById(R.id.profileOcc);
        profDesc = findViewById(R.id.profileDesc);
        Intent mainIntent = getIntent();
        Bundle bundle = mainIntent.getExtras();
        StringBuilder nameString = new StringBuilder(getString(R.string.profile_name));
        StringBuilder ageString = new StringBuilder(getString(R.string.profile_age));
        StringBuilder occString = new StringBuilder(getString(R.string.profile_occ));
        StringBuilder descString = new StringBuilder(getString(R.string.profile_desc));

        if (bundle != null) {
            nameString
                    .append(" ")
                    .append(bundle.getString(Constants.KEY_FNAME).trim())
                    .append(" ")
                    .append(bundle.getString(Constants.KEY_LNAME).trim());
            ageString
                    .append(" ")
                    .append(bundle.getString(Constants.KEY_AGE));
            occString
                    .append(" ")
                    .append(bundle.getString(Constants.KEY_OCC).trim());
            descString
                    .append(" ")
                    .append(bundle.getString(Constants.KEY_DESC));
        }
        profName.setText(nameString);
        profAge.setText(ageString);
        profOcc.setText(occString);
        profDesc.setText(descString);
    }

    public void returnToSignUp(View view) {
        setResult(Constants.CODE_NEW_SIGNUP);
        finish();
    }


}
