package com.example.djackad340.utils;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.djackad340.MainActivity;
import com.example.djackad340.SettingsViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimePickerUtils {
    public static TimePickerDialog.OnTimeSetListener onTimeSetListener (Calendar c,
        StringBuilder timeString, EditText v, SettingsViewModel vm) {
        return (view, hourOfDay, minute) -> {
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);

            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.US);
            timeString.setLength(0);
            timeString.append(sdf.format(c.getTime()));
            vm.updateReminder(String.valueOf(timeString));
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
