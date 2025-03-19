package com.example.gameapp;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SummaryActivity extends AppCompatActivity {
    // View variable
    TextView tv_summary;
    Button bt_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //
        tv_summary = findViewById(R.id.tv_summary);
        bt_home = findViewById(R.id.bt_home);

        //
        Intent intentScore = getIntent();
        int score = intentScore.getIntExtra("key_score", 0);
        String summaryString = intentScore.getStringExtra("key_summary");
        long timeTaken = intentScore.getLongExtra("key_time", 0);

        tv_summary.setText("!!!!GAME OVER!!!! \n Your Score: " + score + "\nSummary: " + summaryString + "\nTime taken: " + timeTaken);

        //
        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(SummaryActivity.this, MainActivity.class);
                startActivity(intentHome);
            }
        });
    }
}