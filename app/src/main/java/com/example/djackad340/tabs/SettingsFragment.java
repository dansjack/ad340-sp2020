package com.example.djackad340.tabs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;

import com.example.djackad340.R;
import com.example.djackad340.Settings;
import com.example.djackad340.SettingsViewModel;

import java.util.Calendar;

import static com.example.djackad340.utils.TimePickerUtils.onTimeSetListener;
import static com.example.djackad340.utils.TimePickerUtils.setTimePickerShowOnClick;


public class SettingsFragment extends Fragment implements TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {
    private EditText matchReminderTime;
    private Spinner matchDistance;
    private Spinner matchGender;
    private Switch typeSwitch;
    private SettingsViewModel mSettingsViewModel;


    private static final String TAG = SettingsFragment.class.getName();

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Resources res = view.getResources();

        Calendar c = Calendar.getInstance();
        StringBuilder timeString = new StringBuilder();

        mSettingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        matchReminderTime = view.findViewById(R.id.match_reminder_value);
        matchDistance = view.findViewById(R.id.distance_spinner);
        matchGender = view.findViewById(R.id.gender_spinner);
        typeSwitch = view.findViewById(R.id.account_type_switch);

        final TimePickerDialog.OnTimeSetListener time = onTimeSetListener(c, timeString, matchReminderTime);
        setTimePickerShowOnClick(this.getContext(), c, matchReminderTime, time);


        if (typeSwitch.isChecked()) {
            typeSwitch.setText(res.getString(R.string.switch_private));
        } else {
            typeSwitch.setText(res.getString(R.string.switch_public));
        }

        typeSwitch.setOnCheckedChangeListener((compoundButton, isPrivate) -> {
            mSettingsViewModel.updatePrivate(true);
            if (isPrivate) {
                typeSwitch.setText(res.getString(R.string.switch_private));
            } else {
                typeSwitch.setText(res.getString(R.string.switch_public));
            }
        });
        initSpinners();

        mSettingsViewModel.getSettings().observe(getViewLifecycleOwner(), settings -> {
            // Update the cached copy of the words in the adapter.
            Log.i(TAG, "onChanged: ");
            Log.i(TAG, String.valueOf(settings.getId()));
            matchReminderTime.setText(settings.getReminderTime());
            matchDistance.setSelection(
                    ((ArrayAdapter) matchDistance.getAdapter()).getPosition(settings.getDistance()));
            matchGender.setSelection(
                    ((ArrayAdapter) matchGender.getAdapter()).getPosition(settings.getGender()));
            typeSwitch.setChecked(settings.getPrivate());
        });
        return view;
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        matchReminderTime.setText(hourOfDay + ":" + minute);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i(TAG, "onItemSelected: ");
        Log.i(TAG, (String) adapterView.getItemAtPosition(i));
        adapterView.setSelection(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.i(TAG, "onNothingSelected: ");
        adapterView.setSelection(2);

    }

    public void initSpinners() {
        // distance
        ArrayAdapter<CharSequence> distanceAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.distance_array, android.R.layout.simple_spinner_item);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matchDistance.setAdapter(distanceAdapter);
        matchDistance.setOnItemSelectedListener(this);

        // gender
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matchGender.setAdapter(genderAdapter);
        matchGender.setOnItemSelectedListener(this);
    }
}
