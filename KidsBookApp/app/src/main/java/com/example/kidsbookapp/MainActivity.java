package com.example.kidsbookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void apple(View view){
        AppleFragment ap = new AppleFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, ap);
        ft.addToBackStack(null);
        ft.commit();
    }
    public void bird(View view){
        BirdFragment ap = new BirdFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, ap);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("pause", "Pausing ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("start", "Starting ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("destroy", "Destroying ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("stop", "Stopping ");
    }
}