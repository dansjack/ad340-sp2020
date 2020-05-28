package com.example.djackad340;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimePickerUtils {

    public static TimePickerDialog.OnTimeSetListener onTimeSetListener ( // MainActivity
        Calendar c, StringBuilder timeString, TextView v) {
        return (view, hourOfDay, minute) -> {
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);

            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.US);
            timeString.setLength(0);
            timeString.append(sdf.format(c.getTime()));
            v.setText(timeString);
        };
    }


    public static void setTimePickerShowOnClick(Context context, Calendar c, Button imageButton,
                                                TimePickerDialog.OnTimeSetListener time) {
        imageButton.setOnClickListener(view ->
                new TimePickerDialog(context, time,
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE), false).show());
    }
}
