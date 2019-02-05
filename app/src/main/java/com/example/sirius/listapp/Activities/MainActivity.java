package com.example.sirius.listapp.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.sirius.listapp.Support.ExitDialog;
import com.example.sirius.listapp.Fragments.MainFragment;
import com.example.sirius.listapp.Fragments.TomorrowFragment;
import com.example.sirius.listapp.Fragments.WeekFragment;
import com.example.sirius.listapp.R;


public class MainActivity extends AppCompatActivity {

    public RecyclerView list;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.main:
                    selectedFragment = new MainFragment();
                    break;
                case R.id.tomorrow:
                    selectedFragment = new TomorrowFragment();
                    break;
                case R.id.week:
                    selectedFragment = new WeekFragment();
                    break;
            }
            assert selectedFragment != null;
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new MainFragment())
                .commit();

        navigation.setSelectedItemId(R.id.main);

    }

    @Override
    public void onBackPressed() {
        ExitDialog exitDialog = new ExitDialog();
        exitDialog.show(getSupportFragmentManager(), "Exit dialog");

    }

}
