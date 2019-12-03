package com.example.sampledagger.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {PersonEntity.class}, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();

    private static AppDatabase INSTANCE = null;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room
                        .databaseBuilder(
                                context.getApplicationContext(),
                                AppDatabase.class,
                                "app_database"
                        ).build();
            }
        }
        return INSTANCE;
    }
}
