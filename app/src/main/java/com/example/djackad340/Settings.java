package com.example.djackad340;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "settings_table")
public class Settings {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;
    public Integer getId() { return id; }

    @ColumnInfo(name = "reminderTime")
    private String mReminderTime;
    public String getReminderTime() {return this.mReminderTime;}
    public void setReminderTime(String reminderTime) {this.mReminderTime = reminderTime;}

    @ColumnInfo(name = "distance")
    private String mDistance;
    public String getDistance() { return this.mDistance;}
    public void setDistance(String distance) {this.mDistance = distance;}

    @ColumnInfo(name = "gender")
    private String mGender;
    public String getGender() { return this.mGender;}
    public void setGender(String gender) {this.mGender = gender;}

    @ColumnInfo(name = "private")
    public Boolean isPrivate;
    public Boolean getPrivate() {return this.isPrivate;}
    public void setPrivate(Boolean isPrivate) {this.isPrivate = isPrivate;}

    @ColumnInfo(name = "ageRange")
    private String mAgeRange;
    public String getAgeRange() {return this.mAgeRange;}
    public void setAgeRange(String ageRange) {this.mAgeRange = ageRange;}


    public Settings(Integer id) {
        this.id = id;
        this.mReminderTime = "7:00 PM";
        this.mDistance = "25";
        this.mGender = "All";
        this.isPrivate = false;
        this.mAgeRange = "18 45";
    }
}
