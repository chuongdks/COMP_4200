package com.example.counterapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declare view variable
    Button btn_count, btn_reset, btn_up, btn_down, btn_swap, btn_next;
    TextView tv_count;
    EditText editText;

    // Other variable
    int counter = 0;

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
        btn_count = findViewById(R.id.btn_count);
        btn_reset = findViewById(R.id.btn_reset);
        btn_up = findViewById(R.id.btn_up);
        btn_down = findViewById(R.id.btn_down);
        btn_swap = findViewById(R.id.btn_swap);
        btn_next = findViewById(R.id.btn_next);
        tv_count = findViewById(R.id.tv_count);
        editText = findViewById(R.id.inputText);

        // Count up button
        btn_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++counter;
                tv_count.setText(String.valueOf(counter));
                btn_reset.setVisibility(View.VISIBLE);
            }
        });

        // Reset button counter
        btn_reset.setOnClickListener(v -> {
            // Toast.makeText(getApplicationContext(), "Value reset to 0", Toast.LENGTH_LONG).show();

            /*https://www.geeksforgeeks.org/how-to-create-an-alert-dialog-box-in-android/*/
            // Create the object of AlertDialog Builder class
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            // Set the message and title for the box
            builder.setMessage("Do you want to reset ?");
            builder.setTitle("Alert !");

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            builder.setCancelable(false);

            // Set the positive button
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                counter = 0;
                tv_count.setText(String.valueOf(counter));
                btn_reset.setVisibility(View.INVISIBLE);
            });
            // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });

            // Create and Show the Alert dialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        // Up btn to get edit Text content
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // counter = ((Integer) editText).getText();
                tv_count.setText(editText.getText().toString());
                editText.setText("");
            }
        });

        // Down btn to get TV Text content
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(tv_count.getText().toString());
                tv_count.setText("");
            }
        });

        // Swap button to swap content btw Edit and Text Viewer
        btn_swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                editText.setText(tv_count.getText().toString());
                tv_count.setText(str);
            }
        });

        // Swap button to swap content btw Edit and Text Viewer
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class); // this means current activity
                startActivity(i);
            }
        });
    }
}