package com.example.activitysample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // Declare view variable
    Button btn_next;
    EditText et_name;

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

        Log.d("create", "Created ");

        // assign View by id
        btn_next = findViewById(R.id.button);
        et_name = findViewById(R.id.editTextText);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSecond = new Intent(MainActivity.this, SecondActivity.class);
                intentSecond.putExtra("nameKey", et_name.getText().toString());
                startActivity(intentSecond);
            }
        });
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