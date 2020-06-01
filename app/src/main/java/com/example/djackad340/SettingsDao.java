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

    @Query("SELECT COUNT(*) FROM settings_table")
    LiveData<Integer> getCount();

    @Query("SELECT * from settings_table")
    LiveData<Settings> getSettings();

    @Query("UPDATE settings_table SET reminderTime = :reminderTime")
    void updateReminderTime(String reminderTime);

    @Query("UPDATE settings_table SET distance = :distance")
    void updateDistance(String distance);

    @Query("UPDATE settings_table SET gender = :gender")
    void updateGender(String gender);

    @Query("UPDATE settings_table SET private = :isPrivate")
    void updatePrivate(Boolean isPrivate);

    @Query("UPDATE settings_table SET ageRange = :ageRange")
    void updateAgeRange(String ageRange);

}