package com.example.djackad340;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SettingsDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert
    void insert(Settings settings);

    @Query("DELETE FROM settings_table")
    void deleteAll();

    @Query("SELECT * from settings_table")
    LiveData<Settings> getSettings();

    @Query("UPDATE settings_table SET reminderTime = :reminderTime WHERE id = :id")
    void updateReminderTime(int id, String reminderTime);

    @Query("UPDATE settings_table SET distance = :distance WHERE id = :id")
    void updateDistance(int id, String distance);

    @Query("UPDATE settings_table SET gender = :gender WHERE id = :id")
    void updateGender(int id, String gender);

    @Query("UPDATE settings_table SET private = :isPrivate WHERE id = :id")
    void updatePrivate(int id, Boolean isPrivate);

    @Query("UPDATE settings_table SET ageRange = :ageRange WHERE id = :id")
    void updateAgeRange(int id, String ageRange);

}