package com.example.sirius.listapp.Database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Meteo_table")
public class Meteo {

    @PrimaryKey(autoGenerate = true)
    private int idtable;

    public String date;
    public String tod;
    public String pressure;
    public String temp;
    public String humidity;
    public String wind;
    public String cloud;

    int getIdtable() {
        return idtable;
    }

    void setIdtable(int idtable) {
        this.idtable = idtable;
    }

}
