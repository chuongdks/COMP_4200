package com.example.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AppleFragment extends Fragment {

    public AppleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("life_cycle", "onAttach in Apple Fragment - Chuong Pham");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("life_cycle", "onCreate in Apple Fragment - Chuong Pham");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("life_cycle", "onCreateView in Apple Fragment - Chuong Pham");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apple, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("life_cycle", "onActivityCreated in Apple Fragment - Chuong Pham");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("life_cycle", "onStart in Apple Fragment - Chuong Pham");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("life_cycle", "onResume in Apple Fragment - Chuong Pham");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("life_cycle", "onPause in Apple Fragment - Chuong Pham");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("life_cycle", "onStop in Apple Fragment - Chuong Pham");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("life_cycle", "onDestroyView in Apple Fragment - Chuong Pham");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("life_cycle", "onDetach in Apple Fragment - Chuong Pham");
    }
}