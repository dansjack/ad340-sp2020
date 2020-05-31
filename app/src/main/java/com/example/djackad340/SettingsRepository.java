package com.example.djackad340;

import android.app.Application;

import androidx.lifecycle.LiveData;

public class SettingsRepository {
    private SettingsDao mSettingsDao;
    private LiveData<Settings> allSettings;

    SettingsRepository(Application application) {
        SettingsDatabase db = SettingsDatabase.getDatabase(application);
        mSettingsDao = db.settingsDao();
        allSettings = mSettingsDao.getSettings();

        // Observed LiveData will notify the observer when the data has changed.
    }

    LiveData<Settings> getAllSettings() {
        return allSettings;
    }

    void insert(Settings settings) {
        SettingsDatabase.databaseWriteExecutor.execute(() -> { mSettingsDao.insert(settings);});
    }

    void updatePrivate(int id, Boolean isPrivate) {
        SettingsDatabase.databaseWriteExecutor.execute(() -> { mSettingsDao.updatePrivate(id, isPrivate);});
    }
}
