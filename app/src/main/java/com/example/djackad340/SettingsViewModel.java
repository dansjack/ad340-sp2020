package com.example.djackad340;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SettingsViewModel extends AndroidViewModel {
    private SettingsRepository mRepository;
    private LiveData<Settings> mSettings;
    private SettingsViewModel mSettingsViewModel;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SettingsRepository(application);
        mSettings = mRepository.getAllSettings();
    }

    public LiveData<Settings> getSettings() { return mSettings; }

    public void insert(Settings settings) { mRepository.insert(settings);}

    public void updatePrivate(Boolean isPrivate) { mRepository.updatePrivate(8, isPrivate);}
}
