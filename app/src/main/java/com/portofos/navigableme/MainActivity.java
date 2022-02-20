package com.portofos.navigableme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // BottomNavigation
    private BottomNavigationView bottomNavigationView;
    // Fragments
    private HomeFragment homeFragment = new HomeFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private MapFragment mapFragment = new MapFragment();
    private ParkFragment parkFragment = new ParkFragment();
    private SettingsFragment settingsFragment = new SettingsFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        // getSupportFragmentManager method is responsible for changing Fragments.
        // onCreate of Application, we want to display the HomeFragment first for the user
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_icon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.search_icon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment).commit();
                        return true;
                    case R.id.map_icon:
                        getSupportFragmentManager().beginTransaction().add(R.id.container,mapFragment).commit();
                        return true;
                    case R.id.park_icon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,parkFragment).commit();
                        return true;
                    case R.id.settings_icon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

}