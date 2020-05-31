package com.example.djackad340;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Settings.class}, version = 1, exportSchema = false)
public abstract class SettingsDatabase extends RoomDatabase {

    public abstract SettingsDao settingsDao();

    private static volatile SettingsDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static SettingsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SettingsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SettingsDatabase.class, "settings_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
