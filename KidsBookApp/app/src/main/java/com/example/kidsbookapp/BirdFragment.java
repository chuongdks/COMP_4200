package com.example.kidsbookapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BirdFragment extends Fragment {

    public BirdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bird, container, false);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("test", "onAttach -- BirdFragments");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("test", "onPause -- BirdFragments");
    }
}