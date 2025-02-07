package com.example.todo_list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AddAndEditActivity extends AppCompatActivity {
    // Declare variable
    TextView tv_title;
    EditText et_edit_task;
    Button btn_add_task;
    ConstraintLayout layout;

    // other variable
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_and_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // assign View by id
        layout = findViewById(R.id.main2);
        tv_title = findViewById(R.id.tv_title);
        et_edit_task = findViewById(R.id.et_edit_task);
        btn_add_task = findViewById(R.id.btn_add_task);

        // send data back to main activity when button is clicked
        btn_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskText = et_edit_task.getText().toString();

                // Display Snackbar if editText is empty, else use getSharedPreference to send data back to main activity
                if (taskText.isEmpty()) {
                    // Display Snackbar
                    Snackbar.make(layout, "No Empty Input!", Snackbar.LENGTH_LONG).show();
                }
                else {
                    // Using getSharedPreference method to send data from second activity to main (https://developer.android.com/reference/android/content/Context#getSharedPreferences(java.lang.String,%20int))
                    sp = getSharedPreferences("TASK_ADDER", MODE_PRIVATE);
                    spe = sp.edit();
                    spe.putString("taskKey", taskText);
                    spe.apply(); // Save the value
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Use intent to retrieve data from main to this activity
        Intent intentRetrieve = getIntent();
        String taskMsgEdit = intentRetrieve.getStringExtra("taskEditKey");
        int taskMsgPosition = intentRetrieve.getIntExtra("taskPositionKey", -1);

        // Check if task
        if (taskMsgEdit != null) {
            et_edit_task.setText(taskMsgEdit);
            // Change the button text to indicate an edit action
            btn_add_task.setText("Update Task");
            tv_title.setText("Task Editing");
        }

        // send data back to main activity when button is clicked
        btn_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskText = et_edit_task.getText().toString();

                // Display Snackbar if editText is empty, else use getSharedPreference to send data back to main activity
                if (taskText.isEmpty()) {
                    // Display Snackbar
                    Snackbar.make(layout, "No Empty Input!", Snackbar.LENGTH_LONG).show();
                }
                else {
                    // Using getSharedPreference method to send data from second activity to main (https://developer.android.com/reference/android/content/Context#getSharedPreferences(java.lang.String,%20int))
                    sp = getSharedPreferences("TASK_ADDER", MODE_PRIVATE);
                    spe = sp.edit();
                    spe.putString("taskKey", taskText);
                    spe.putInt("taskPosition", taskMsgPosition);
                    spe.apply(); // Save the value
                    finish();
                }
            }
        });
    }
}