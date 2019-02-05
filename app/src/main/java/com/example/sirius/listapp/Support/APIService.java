package com.example.sirius.listapp.Support;

import com.example.sirius.listapp.Database.Meteo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    // ХОСТ сервера
    String HOST = "http://icomms.ru";

    // ОБРАЗЕЦ ГЕТ ЗАПРОСА
    @GET("inf/meteo.php")
    // Вызов tid из JSON по id
    Call<List<Meteo>> getMeteo(@Query("tid") int tid);

}
