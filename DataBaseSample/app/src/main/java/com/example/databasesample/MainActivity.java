package com.example.databasesample;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //
    Button addButton, editButton, deleteButton, displayButton;
    EditText titleText, descText;

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
        addButton = findViewById(R.id.bt_add);
        editButton = findViewById(R.id.bt_edit);
        deleteButton = findViewById(R.id.bt_delete);
        displayButton = findViewById(R.id.bt_display);
        titleText = findViewById(R.id.et_title);
        descText = findViewById(R.id.et_description);

        //
        DBHelper db = new DBHelper(getApplicationContext(), "db_example", null, 1);
        db.getReadableDatabase();

        //
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleText.getText().toString();
                String description = descText.getText().toString();

                long rowInfo = db.addData(title, description);
                if (rowInfo < 0) {
                    Toast.makeText(getApplicationContext(), "Not Added yet.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Added!", Toast.LENGTH_SHORT).show();
                    titleText.setText("");
                    descText.setText("");
                }
            }
        });

        //
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.displayData();
                String cursorInfo = "";
                if (cursor.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Waiting to display!", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (cursor.moveToNext()) {
                        cursorInfo += "\nTitle: " + cursor.getString(1) + " Description: " + cursor.getString(2);
                    }
                }

                AlertDialog.Builder alertMessage = new AlertDialog.Builder(MainActivity.this);
                alertMessage.setTitle("Database info");
                alertMessage.setMessage(cursorInfo);
                alertMessage.setCancelable(true);
                alertMessage.show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteData(titleText.getText().toString());
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleText.getText().toString();
                String description = descText.getText().toString();

                long rowInfo = db.updateData(title, description);
            }
        });
    }
}