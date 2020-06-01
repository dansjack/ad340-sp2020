package com.example.djackad340.tabs;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;

import com.example.djackad340.R;
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

    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Calendar c = Calendar.getInstance();
        StringBuilder timeString = new StringBuilder();

        mSettingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        matchReminderTime = view.findViewById(R.id.match_reminder_value);
        matchDistance = view.findViewById(R.id.distance_spinner);
        matchGender = view.findViewById(R.id.gender_spinner);
        typeSwitch = view.findViewById(R.id.account_type_switch);

        final TimePickerDialog.OnTimeSetListener time = onTimeSetListener(c, timeString, matchReminderTime);
        setTimePickerShowOnClick(this.getContext(), c, matchReminderTime, time);

        typeSwitch.setOnCheckedChangeListener(
                (compoundButton, isPrivate) -> {
                    mSettingsViewModel.updatePrivate(isPrivate);
                    Log.i(TAG, "onCreateView: hello");
                });
        initSpinners();

        mSettingsViewModel.getSettings().observe(getViewLifecycleOwner(), settings -> {
            // Update the cached copy of the words in the adapter.
            Log.i(TAG, settings.getDistance());
            Log.i(TAG, settings.getGender());

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
        adapterView.setSelection(i);
        if (mSettingsViewModel.getSettings().getValue() != null) {
            if (adapterView.getId() == R.id.distance_spinner) {
                Log.i(TAG, "onItemSelected: setDistance");
                Log.i(TAG, mSettingsViewModel.getSettings().getValue().getDistance());
                mSettingsViewModel.updateDistance((String) adapterView.getItemAtPosition(i));
            } else if (adapterView.getId() == R.id.gender_spinner) {
                Log.i(TAG, "onItemSelected: setGender");
                Log.i(TAG, mSettingsViewModel.getSettings().getValue().getGender());

                mSettingsViewModel.updateGender((String) adapterView.getItemAtPosition(i));
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        adapterView.setSelection(2);
        Log.i(TAG, "onNothingSelected: ");

    }

    private void initSpinners() {
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
