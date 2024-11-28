package com.example.miammiam.bd;

import android.content.Context;
import androidx.room.Room;

public class DatabaseSingleton {

    private static AppDatabase instance;

    private DatabaseSingleton() {
        // Impede a criação de instâncias
    }

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "miammiam_database"
            ).build();
        }
        return instance;
    }
}
