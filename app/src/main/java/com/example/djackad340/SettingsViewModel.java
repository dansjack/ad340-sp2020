package com.example.djackad340;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SettingsViewModel extends AndroidViewModel {
    private SettingsRepository mRepository;
    private LiveData<Settings> mSettings;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SettingsRepository(application);
        mSettings = mRepository.getAllSettings();
    }

    public LiveData<Settings> getSettings() { return mSettings; }

    public LiveData<Integer> getCount() { return mRepository.getCount();}

    public void updateReminder(String matchReminder) {mRepository.updateReminder(matchReminder);}

    public void updateDistance(String matchDistance) { mRepository.updateDistance(matchDistance);}

    public void updateGender(String gender) { mRepository.updateGender(gender);}

    public void updatePrivate(Boolean isPrivate) { mRepository.updatePrivate(isPrivate);}

    public void updateMinAge(String minAge) { mRepository.updateMinAge(minAge);}

    public void updateMaxAge(String maxAge) { mRepository.updateMaxAge(maxAge);}

}
