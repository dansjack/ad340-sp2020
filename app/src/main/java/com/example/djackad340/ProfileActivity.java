package com.example.djackad340;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    public ProfileActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView profName = findViewById(R.id.profileName);
        TextView profAgeLoc = findViewById(R.id.profileAge);
        TextView profOcc = findViewById(R.id.profileOcc);
        TextView profDesc = findViewById(R.id.profileDesc);
        Intent mainIntent = getIntent();
        Bundle bundle = mainIntent.getExtras();
        StringBuilder nameString = new StringBuilder();
        StringBuilder ageLocString = new StringBuilder();
        StringBuilder occString = new StringBuilder();
        StringBuilder descString = new StringBuilder(getString(R.string.profile_desc));

        if (bundle != null) {
            nameString
                    .append(bundle.getString(Constants.KEY_FNAME).trim())
                    .append(" ")
                    .append(bundle.getString(Constants.KEY_LNAME).trim());
            ageLocString
                    .append(" ")
                    .append(bundle.getString(Constants.KEY_AGE))
                    .append(", ")
                    .append(bundle.getString(Constants.KEY_LOC).trim());
            occString
                    .append(" ")
                    .append(bundle.getString(Constants.KEY_OCC).trim());
            descString
                    .append(" ")
                    .append(bundle.getString(Constants.KEY_DESC));
        }
        profName.setText(nameString);
        profAgeLoc.setText(ageLocString);
        profOcc.setText(occString);
        profDesc.setText(descString);
    }

    public void returnToSignUp(View view) {
        setResult(Constants.CODE_NEW_SIGNUP);
        finish();
    }


}
