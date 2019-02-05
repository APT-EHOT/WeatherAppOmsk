package com.example.sirius.listapp.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MeteoDAO {

    @Insert
    void insert(Meteo meteo);

    @Query("DELETE FROM Meteo_table")
    void deleteAllMeteo();

    // ПОКАЗЫВАЕТ ПЕРВЫЕ ЧЕТЫРЕ СТРОКИ ТАБЛИЦЫ ЦЕЛИКОМ, ПРОПУСКАЯ 4 ПРЕДЫДУЩИЕ (по айдишнику)
    @Query("SELECT * FROM Meteo_table ORDER BY idtable DESC LIMIT 4 OFFSET 4")
    LiveData<List<Meteo>> getTodayMeteo();

    @Query("SELECT * FROM Meteo_table ORDER BY idtable DESC LIMIT 4 OFFSET 8")
    LiveData<List<Meteo>> getTomorrowMeteo();

    @Query("SELECT * FROM Meteo_table ORDER BY idtable DESC LIMIT 24 OFFSET 12")
    LiveData<List<Meteo>> getWeekMeteo();
}
