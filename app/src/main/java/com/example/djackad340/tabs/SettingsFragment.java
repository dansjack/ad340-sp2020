package com.example.djackad340.tabs;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.djackad340.R;
import com.example.djackad340.SettingsViewModel;

import java.util.Calendar;
import java.util.Objects;

import static com.example.djackad340.utils.TimePickerUtils.onTimeSetListener;
import static com.example.djackad340.utils.TimePickerUtils.setTimePickerShowOnClick;


public class SettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private EditText matchReminderTime;
    private Spinner matchDistance;
    private Spinner matchGender;
    private Switch typeSwitch;
    private EditText minAge;
    private EditText maxAge;
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
        minAge = view.findViewById(R.id.min_age);
        maxAge = view.findViewById(R.id.max_age);

        final TimePickerDialog.OnTimeSetListener time = onTimeSetListener(
                c, timeString, matchReminderTime, mSettingsViewModel);
        setTimePickerShowOnClick(this.getContext(), c, matchReminderTime, time);

        typeSwitch.setOnCheckedChangeListener(
                (compoundButton, isPrivate) -> { mSettingsViewModel.updatePrivate(isPrivate); });
        initSpinners();

        mSettingsViewModel.getSettings().observe(getViewLifecycleOwner(), settings -> {
            // Update the cached copy of the words in the adapter.

            if (settings != null) {
                ArrayAdapter<String> distanceList = (ArrayAdapter<String>) matchDistance.getAdapter();
                matchDistance.setSelection(distanceList.getPosition(settings.getDistance()));
                matchReminderTime.setText(settings.getReminderTime());

                ArrayAdapter<String> genderList = (ArrayAdapter<String>) matchGender.getAdapter();
                matchGender.setSelection(genderList.getPosition(settings.getGender()));

                typeSwitch.setChecked(settings.getPrivate());

                if (!settings.getMinAge().contentEquals(minAge.getText())) {
                    minAge.setText(settings.getMinAge());
                }

                if (!settings.getMaxAge().contentEquals(maxAge.getText())) {
                    maxAge.setText(settings.getMaxAge());
                }
            }

        });

        mSettingsViewModel.getCount().observe(getViewLifecycleOwner(), count -> {
            Log.i(TAG, String.valueOf(mSettingsViewModel.getCount().getValue()));
        });

        minAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String newMinAge = String.valueOf(minAge.getText());

                if (!newMinAge.equals("") && (Integer.parseInt(newMinAge) < 18
                        || Integer.parseInt(newMinAge) > 99 )) {
                    Toast.makeText(view.getContext(), R.string.min_age_toast, Toast.LENGTH_SHORT).show();
                }

                mSettingsViewModel.updateMinAge(newMinAge);
            }
        });

        maxAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String newMaxAge = String.valueOf(maxAge.getText());

                if (!newMaxAge.equals("") && (Integer.parseInt(newMaxAge) < 18
                        || Integer.parseInt(newMaxAge) > 99 )) {
                    Toast.makeText(view.getContext(), R.string.max_age_toast, Toast.LENGTH_SHORT).show();
                }
                mSettingsViewModel.updateMaxAge(newMaxAge);
            }
        });
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.setSelection(i);
        if (mSettingsViewModel.getSettings().getValue() != null) {
            if (adapterView.getId() == R.id.distance_spinner) {
                mSettingsViewModel.updateDistance((String) adapterView.getItemAtPosition(i));
            } else if (adapterView.getId() == R.id.gender_spinner) {
                mSettingsViewModel.updateGender((String) adapterView.getItemAtPosition(i));
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { adapterView.setSelection(2); }

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
