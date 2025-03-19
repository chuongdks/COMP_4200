package com.example.gameapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // Define Views
    Button bt_add, bt_sub, bt_mul, bt_div, bt_rand;

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

        //
        bt_add = findViewById(R.id.bt_add);
        bt_sub = findViewById(R.id.bt_sub);
        bt_mul = findViewById(R.id.bt_mul);
        bt_div = findViewById(R.id.bt_div);
        bt_rand = findViewById(R.id.bt_rand);

        // Switch to Question activity on btn_add click
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startnActivityBasedOnMath("addition");
            }
        });

        bt_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startnActivityBasedOnMath("subtraction");
            }
        });

        bt_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startnActivityBasedOnMath("multiplication");
            }
        });

        bt_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startnActivityBasedOnMath("division");
            }
        });

        bt_rand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startnActivityBasedOnMath("random");
            }
        });
    }

    // Method to start QuestionActivity with operation type
    void startnActivityBasedOnMath(String operator) {
        Intent intentQuestion = new Intent(MainActivity.this, QuestionActivity.class);
        intentQuestion.putExtra("operator", operator); // Pass the operation type
        startActivity(intentQuestion);
    }
}