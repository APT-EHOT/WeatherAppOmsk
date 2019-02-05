package com.example.sirius.listapp.Fragments;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sirius.listapp.Database.Meteo;
import com.example.sirius.listapp.Support.MeteoAdapter;
import com.example.sirius.listapp.Database.MeteoRepository;
import com.example.sirius.listapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainFragment extends Fragment {

    public RecyclerView list;

    private MeteoAdapter meteoAdapter;


    List<Meteo> gettedMeteo;
    View v;
    Application application;


    public MainFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_main, container, false);
        list = v.findViewById(R.id.list);
        gettedMeteo = new ArrayList<>();

        list.setVisibility(View.VISIBLE);

        application = Objects.requireNonNull(getActivity()).getApplication();
        MeteoRepository meteoRepository = new MeteoRepository(application);

        LiveData<List<Meteo>> liveData = meteoRepository.getTodayMeteo();

        liveData.observe(MainFragment.this, new Observer<List<Meteo>>() {
            @Override
            public void onChanged(@Nullable List<Meteo> meteos) {
                gettedMeteo = meteos;
                meteoAdapter = new MeteoAdapter(meteos);
                list.setAdapter(meteoAdapter);

            }
        });


        list.setLayoutManager(new LinearLayoutManager(
                getActivity().getApplicationContext()));

        return v;
    }

}
