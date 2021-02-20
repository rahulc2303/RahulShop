package com.example.shopisthan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity
{
    private BottomNavigationView bottomNavigationView121;
    private NavController navController121;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView121 = findViewById(R.id.bottomNavigationView);
        navController121 = Navigation.findNavController(this,R.id.fragment_container);

        NavigationUI.setupWithNavController(bottomNavigationView121, navController121);

    }
}