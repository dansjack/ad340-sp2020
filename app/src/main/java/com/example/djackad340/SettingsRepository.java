package com.example.djackad340;

import android.app.Application;

import androidx.lifecycle.LiveData;

public class SettingsRepository {
    private SettingsDao mSettingsDao;
    private LiveData<Settings> allSettings;
    private LiveData<Integer> settingsCount;

    SettingsRepository(Application application) {
        SettingsDatabase db = SettingsDatabase.getDatabase(application);
        mSettingsDao = db.settingsDao();
        allSettings = mSettingsDao.getSettings();
        settingsCount = mSettingsDao.getCount();

        // Observed LiveData will notify the observer when the data has changed.
    }

    LiveData<Settings> getAllSettings() {
        return allSettings;
    }

    void insert(Settings settings) {
        SettingsDatabase.databaseWriteExecutor.execute(() -> { mSettingsDao.insert(settings);});
    }

    LiveData<Integer> getCount() {
        return settingsCount;
    }

    void updateReminder(String matchReminder) {
        SettingsDatabase.databaseWriteExecutor.execute(() -> { mSettingsDao.updateReminderTime(matchReminder);});
    }

    void updateDistance(String matchDistance) {
        SettingsDatabase.databaseWriteExecutor.execute(() -> { mSettingsDao.updateDistance(matchDistance);});
    }

    void updateGender(String gender) {
        SettingsDatabase.databaseWriteExecutor.execute(() -> { mSettingsDao.updateGender(gender);});
    }

    void updatePrivate(Boolean isPrivate) {
        SettingsDatabase.databaseWriteExecutor.execute(() -> { mSettingsDao.updatePrivate(isPrivate);});
    }

    void updateAgeRange(String ageRange) {
        SettingsDatabase.databaseWriteExecutor.execute(() -> { mSettingsDao.updateAgeRange(ageRange);});
    }
}
