package com.example.sirius.listapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.sirius.listapp.Database.Meteo;
import com.example.sirius.listapp.Database.MeteoRepository;
import com.example.sirius.listapp.R;
import com.example.sirius.listapp.Support.APIService;


import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoaderActivity extends AppCompatActivity {

    private MeteoRepository meteoRepository;
    private List<Meteo> meteos;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        tv = findViewById(R.id.tv_loader);

        meteoRepository = new MeteoRepository(getApplication());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.HOST)
                .addConverterFactory(GsonConverterFactory
                        .create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        Call<List<Meteo>> call = apiService.getMeteo(44);

        call.enqueue(new Callback<List<Meteo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meteo>> call,
                                   @NonNull Response<List<Meteo>> response) {

                meteoRepository.deleteAllMeteo();
                meteos = response.body();
                Collections.reverse(meteos);
                //Log.d("MyLog", Integer.toString(meteos.size()));
                Log.d("MyLog", "ЗАПРОС ПОЛУЧЕН");
                for (Meteo meteo : meteos) {
                    meteoRepository.insert(meteo);
                }

                // КАЛИБРОВОЧНАЯ ЗАДЕРЖКА НА ВРЕМЯ ЗАГРУЗКИ
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Кэширование данных...");
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(
                                        LoaderActivity.this,
                                        MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);

                    }
                }, 1000);
            }

            @Override
            public void onFailure(@NonNull Call<List<Meteo>> call,
                                  @NonNull Throwable t) {
                Log.d("MyLog", "ЗАПРОС ПРОЁБАН");
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Данные не получены! Воспроизвожу из кэша...");
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(
                                        LoaderActivity.this,
                                        MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);
                    }
                }, 1000);
            }
        });
    }
}
