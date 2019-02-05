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
import com.example.sirius.listapp.Database.MeteoRepository;
import com.example.sirius.listapp.R;
import com.example.sirius.listapp.Support.MeteoAdapter;

import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeekFragment extends Fragment {

    public RecyclerView list;

    private MeteoAdapter meteoAdapter;

    View v;
    Application application;


    public WeekFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_main, container, false);
        list = v.findViewById(R.id.list);

        list.setVisibility(View.VISIBLE);

        application = Objects.requireNonNull(getActivity()).getApplication();
        MeteoRepository meteoRepository = new MeteoRepository(application);

        LiveData<List<Meteo>> liveData = meteoRepository.getWeekMeteo();
        liveData.observe(WeekFragment.this, new Observer<List<Meteo>>() {
            @Override
            public void onChanged(@Nullable List<Meteo> meteos) {
                meteoAdapter = new MeteoAdapter(meteos);
                list.setAdapter(meteoAdapter);
                list.setVisibility(View.VISIBLE);
            }
        });
        list.setLayoutManager(new LinearLayoutManager(
                getActivity().getApplicationContext()));
        // Inflate the layout for this fragment
        return v;
    }

}
