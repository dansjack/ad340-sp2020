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
    }

    LiveData<Settings> getAllSettings() {
        return allSettings;
    }

    LiveData<Integer> getCount() { return settingsCount; }

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

    void updateMinAge(String minAge) {
        SettingsDatabase.databaseWriteExecutor.execute(() -> { mSettingsDao.updateMinAge(minAge);});
    }

    void updateMaxAge(String maxAge) {
        SettingsDatabase.databaseWriteExecutor.execute(() -> { mSettingsDao.updateMaxAge(maxAge);});
    }
}
