package com.example.todo_list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Declare variable
    ListView listView;
    EditText editText;
    Button btn_add_task;
    LinearLayout layout;
    FloatingActionButton fab_add;

    // other variable
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    FileHandler fileHandler = new FileHandler();
    final String FILE_NAME = "saveList.dat";
    SharedPreferences sp;
    SharedPreferences.Editor spe;

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
        layout = findViewById(R.id.main);
        listView = findViewById(R.id.listview);
        btn_add_task = findViewById(R.id.btn_add_task);
        editText = findViewById(R.id.et_edit_task);
        fab_add = findViewById(R.id.fab_add);

        // Read data from file and assigned to ArrayList
        arrayList = fileHandler.readData(FILE_NAME, MainActivity.this);

        // Initialize ArrayAdapter
        arrayAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                arrayList);
        listView.setAdapter(arrayAdapter);

        // fab button to switch to add task activity
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSecond = new Intent(MainActivity.this, AddAndEditActivity.class); // this means current activity
                startActivity(intentSecond);
            }
        });

        // Edit on long click
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedListText = arrayList.get(position);

                // Change the intent to edit but still use the same Activity
                Intent intentEdit = new Intent(MainActivity.this, AddAndEditActivity.class); // this means current activity
                intentEdit.putExtra("taskEditKey", selectedListText);
                intentEdit.putExtra("taskPositionKey", position);
                startActivity(intentEdit);
                return true; // return True so that it doesn't affect setOnItemClickListener of listView
            }
        });

        // Delete based on List View
        listView.setOnItemClickListener (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int i, long arg3) {
                arrayList.remove(i);
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Task Added!", Toast.LENGTH_LONG);

                // Update data to the file if delete item in listView
                fileHandler.writeData(FILE_NAME, arrayList, MainActivity.this);
            }
        });

        // This section is for edit Task on the same Activity (Copy code from line 136 down below)
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Using getSharedPreference method to transfer data from second activity to main (https://developer.android.com/reference/android/content/Context#getSharedPreferences(java.lang.String,%20int))
        // Or use startActivityForResult() (https://stackoverflow.com/questions/14292398/how-to-pass-data-from-2nd-activity-to-1st-activity-when-pressed-back-android)
        sp = getSharedPreferences("TASK_ADDER", MODE_PRIVATE);
        spe = sp.edit();
        String taskMsg = sp.getString("taskKey", null);
        int taskPosition = sp.getInt("taskPosition", -1);

        // Check if taskMsg is null, or else crash program
        if (taskMsg != null) {
            // Add task if position is the default value (-1). Update task if position is a number
            if (taskPosition != -1) { 
                arrayList.set(taskPosition, taskMsg);
            }
            else {
                arrayList.add(taskMsg);
            }

            // arrayAdapter notify the changes
            arrayAdapter.notifyDataSetChanged();
            spe.remove("taskKey");
            spe.commit();
        }
    }
}

//        // add based on edit Text
//        btn_add_task.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String taskText = editText.getText().toString();
//                if (taskText.isEmpty()) {
//                    // Toast.makeText(getApplicationContext(), "No!", Toast.LENGTH_LONG).show();
//
//                    // Create a snack bar
//                    Snackbar.make(layout, "No Empty Input!", Snackbar.LENGTH_LONG).show();
//                }
//                else {
//                    arrayList.add(taskText);
//                    editText.setText("");
//                    arrayAdapter.notifyDataSetChanged();
//                    Toast.makeText(getApplicationContext(), "Task Added!", Toast.LENGTH_LONG).show();
//
//                    // Save data everytime task is added
//                    fileHandler.writeData(FILE_NAME, arrayList, MainActivity.this);
//                }
//            }
//        });
//
//        // Edit on long click
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedListText = arrayList.get(position);
//                editText.setText(selectedListText);
//
//                // Change the button text to indicate an edit action
//                btn_add_task.setText("Update Task");
//
//                // Add button for the update view list mode
//                btn_add_task.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String taskText = editText.getText().toString();
//                        if (taskText.isEmpty()) {
//                            // Create a snack bar
//                            Snackbar snackbar
//                                    = Snackbar
//                                    .make(layout,
//                                            "No Empty Input!",
//                                            Snackbar.LENGTH_LONG)
//                                    .setAction("OK", new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            Snackbar snackbarView = Snackbar.make(layout,
//                                                    "Testing",
//                                                    Snackbar.LENGTH_LONG);
//                                            snackbarView.show();
//                                        }
//                                    });
//                            snackbar.show();
//                        }
//                        else {
//                            arrayList.set(position, taskText);
//                            editText.setText("");
//                            arrayAdapter.notifyDataSetChanged();
//                            btn_add_task.setText("Add Task");
//                            Toast.makeText(getApplicationContext(), "Task Updated!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//
//                return true; // return True so that it doesn't affect setOnItemClickListener of listView
//            }
//        });