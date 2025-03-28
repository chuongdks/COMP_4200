package com.example.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Function to change and apply Fragment
    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("life_cycle", "onStart in Main Activity - Chuong Pham");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("life_cycle", "onResume in Main Activity - Chuong Pham");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("life_cycle", "onPause in Main Activity - Chuong Pham");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("life_cycle", "onStop in Main Activity - Chuong Pham");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("life_cycle", "onDestroy in Main Activity - Chuong Pham");
    }

    // Apple Fragment Function
    public void appleFoo(View view) {
        AppleFragment ap1 = new AppleFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, ap1);
        ft.addToBackStack(null);
        ft.commit();
    }

    // Apple Fragment Function
    public void birdFoo(View view) {
        BirdFragment b1 = new BirdFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, b1);
        ft.addToBackStack(null);
        ft.commit();
    }

    // Apple Fragment Function
    public void catFoo(View view) {
        CatFragment c1 = new CatFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, c1);
        ft.addToBackStack(null);
        ft.commit();
    }
}