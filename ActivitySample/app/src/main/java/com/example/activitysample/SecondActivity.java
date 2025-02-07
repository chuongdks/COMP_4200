package com.example.activitysample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    //
    TextView tv_name;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("create", "Creating 2");

        // assign View by id
        btn_back = findViewById(R.id.button2);
        tv_name = findViewById(R.id.textView);

        Intent intentRetrieve = getIntent();
        String msg = intentRetrieve.getStringExtra("nameKey");
        tv_name.setText("Hello " + msg + " you stupid mf!");

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intentBack);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("pause", "Pausing 2");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("start", "Starting 2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("destroy", "Destroying 2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("stop", "Stopping 2");
    }
}