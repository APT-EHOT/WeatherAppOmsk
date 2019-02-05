package com.example.sirius.listapp.Database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@android.arch.persistence.room.Database(entities = {Meteo.class}, version = 1)

public abstract class MeteoDB extends RoomDatabase {

    private static MeteoDB instance;

    public abstract MeteoDAO meteoDAO();

    public static synchronized MeteoDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MeteoDB.class,
                    "Meteo_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
