package com.example.djackad340;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.myButton);
        final ImageView myImg = findViewById(R.id.simpsons);
        button.setOnClickListener(new View.OnClickListener() {
            boolean btnIsPressed = false;
            public void onClick(View v) {
                if (btnIsPressed) {
                    myImg.setImageResource(R.drawable.things_are_fine);
                    myImg.setContentDescription(getResources().getString(R.string.simpsons_fine));
                    btnIsPressed = false;
                } else {
                    myImg.setImageResource(R.drawable.hell);
                    myImg.setContentDescription(getResources().getString(R.string.simpsons_hell));
                    btnIsPressed = true;
                }
            }
        });
    }
}
