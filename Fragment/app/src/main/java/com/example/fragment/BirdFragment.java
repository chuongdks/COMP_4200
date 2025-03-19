package com.example.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BirdFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BirdFragment newInstance(String param1, String param2) {
        BirdFragment fragment = new BirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d("life_cycle", "onCreate in Bird Fragment - Chuong Pham");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("life_cycle", "onCreateView in Bird Fragment - Chuong Pham");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bird, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("life_cycle", "onResume in Bird Fragment - Chuong Pham");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("life_cycle", "onPause in Bird Fragment - Chuong Pham");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("life_cycle", "onDestroyView in Bird Fragment - Chuong Pham");
    }
}