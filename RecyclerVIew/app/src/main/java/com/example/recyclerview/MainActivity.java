package com.example.recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declare variable
    RecyclerView recyclerView;

    // other variable
    ArrayList<MyDataSet> dataSets = new ArrayList<>();

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

        // assign View by id
        recyclerView = findViewById(R.id.rec_view);

        // Layout???
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Adding text and image to the Recycler View
        dataSets.add(new MyDataSet("Card 1", R.drawable.image1));
        dataSets.add(new MyDataSet("Card 2", R.drawable.image2));
        dataSets.add(new MyDataSet("Card 3", R.drawable.image3));

        // Set Adapter for Recycler view
        MyAdapter myAdapter = new MyAdapter(dataSets, MainActivity.this);
        recyclerView.setAdapter(myAdapter);
    }
}