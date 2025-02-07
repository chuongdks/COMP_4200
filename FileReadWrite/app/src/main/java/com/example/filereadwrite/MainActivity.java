package com.example.filereadwrite;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Declare variable
    ListView listView;
    Button btn_save, btn_load;

    // other variable
    ArrayList<String> dataList;
    ArrayAdapter<String> arrayAdapter;

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
        listView = findViewById(R.id.listview);
        btn_save = findViewById(R.id.btn_save);
        btn_load = findViewById(R.id.btn_load);

        // Save data from list view to file
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList = new ArrayList<String>();
                dataList.add("Example 1");
                dataList.add("Example 2");
                dataList.add("Example 3");

                // ObjectOutputStream to write data to the file
                ObjectOutputStream oos = null;

                try {
                    // open the FileOutputStream using the specified filename (data.dat)
                    FileOutputStream fos = openFileOutput("data.dat", Context.MODE_PRIVATE);

                    // wrap the FileOutputStream with ObjectOutputStream
                    oos = new ObjectOutputStream(fos);

                    // Write data to the file using writeObject()
                    oos.writeObject(dataList);

                    // Toast message
                    Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_LONG).show();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    // close the OOS
                    try {
                        if (oos != null) {
                            oos.close();
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Load data from file button
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize the input streams
                FileInputStream fis = null;
                ObjectInputStream ois = null;

                // Read data from file
                try {
                    // File Stream open the file
                    fis = openFileInput("data.dat");

                    // wrap the FileStream with ObjectStream
                    ois = new ObjectInputStream(fis);

                    // Read data from input stream into ArrayList
                    dataList = (ArrayList<String>)  ois.readObject();
                }
                catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                finally {
                    // close the OOS
                    try {
                        if (ois != null) {
                            ois.close();
                        }
                        if (fis != null) {
                            fis.close();
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                // Populate the listView with data
                listView.setAdapter(new ArrayAdapter<>(MainActivity.this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        dataList));
            }
        });
    }
}