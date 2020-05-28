package com.example.djackad340;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static com.example.djackad340.TimePickerUtils.onTimeSetListener;
import static com.example.djackad340.TimePickerUtils.setTimePickerShowOnClick;


public class SettingsFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {
    private Button matchReminderTime;
    private TextView timeText;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Calendar c = Calendar.getInstance();
        StringBuilder timeString = new StringBuilder();
        timeText = view.findViewById(R.id.match_reminder_text);
        matchReminderTime = view.findViewById(R.id.match_reminder_value);


        final TimePickerDialog.OnTimeSetListener time = onTimeSetListener(c, timeString, timeText);
        setTimePickerShowOnClick(this.getContext(), c, matchReminderTime, time);


        return view;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        matchReminderTime.setText(hourOfDay + ":" + minute);
    }

}
