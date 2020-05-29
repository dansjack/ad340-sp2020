package com.example.djackad340.utils;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.djackad340.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimePickerUtils {
    private static final String TAG = TimePickerUtils.class.getSimpleName();

    public static TimePickerDialog.OnTimeSetListener onTimeSetListener ( // MainActivity
        Calendar c, StringBuilder timeString, EditText v) {
        return (view, hourOfDay, minute) -> {
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);

            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.US);
            timeString.setLength(0);
            timeString.append(sdf.format(c.getTime()));
            v.setText(timeString);
        };
    }


    public static void setTimePickerShowOnClick(Context context, Calendar c, EditText v,
                                                TimePickerDialog.OnTimeSetListener time) {
        v.setOnClickListener(view ->
                new TimePickerDialog(context, time,
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE), false).show());
    }
}
