package com.example.djackad340;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Settings {

    @ColumnInfo(name = "reminderTime")
    private String mReminderTime;

    @ColumnInfo(name = "distance")
    private String mDistance;

    @ColumnInfo(name = "gender")
    private String mGender;

    @ColumnInfo(name = "isPrivate")
    private Boolean mPrivate;

    @ColumnInfo(name = "ageRange")
    private String mAgeRange;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Settings() {}

    public String getReminderTime() {return this.mReminderTime;}

    public String getmDistance() { return this.mDistance;}

    public String getmGender() { return this.mGender;}

    public Boolean getmPrivate() {return this.mPrivate;}

    public String getmAgeRange() {return this.mAgeRange;}
}
