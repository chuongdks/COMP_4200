package com.example.counterapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    // Declare view variable
    Switch sw_nightmode;
    TextView tv_title, tv_status_text, tv_term;
    EditText et_name_text, et_desc_text;
    RadioGroup radio_group_status;
    CalendarView calendarView;
    CheckBox checkBoxTerm;
    FloatingActionButton fab_submit;

    // Other variable
    boolean isNightMode = false;
    String descriptionText = "";
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // assign View by id
        sw_nightmode = findViewById(R.id.sw_nightmode);
        et_name_text = findViewById(R.id.et_name_text);
        et_desc_text = findViewById(R.id.et_desc_text);
        tv_title = findViewById(R.id.tv_title);
        tv_status_text = findViewById(R.id.tv_status_text);
        tv_term = findViewById(R.id.tv_term);
        radio_group_status = findViewById(R.id.radio_group_status);
        calendarView = findViewById(R.id.calendarView);
        checkBoxTerm = findViewById(R.id.checkbox_term);
        fab_submit = findViewById(R.id.fab_submit);

        // Change to night mode using Switch
        sw_nightmode.setOnCheckedChangeListener((buttonView, isChecked) -> { // https://developer.android.com/reference/android/widget/CompoundButton
            isNightMode = isChecked;
            findViewById(R.id.main).setBackgroundColor(isChecked ? Color.DKGRAY : Color.WHITE);

            // Change text color
            et_name_text.setTextColor(isChecked? Color.WHITE : Color.BLACK); // inverse screen color and text color
            et_desc_text.setTextColor(isChecked? Color.WHITE : Color.BLACK); // inverse screen color and text color
            tv_title.setTextColor(isChecked? Color.WHITE : Color.BLACK); // inverse screen color and text color
            tv_status_text.setTextColor(isChecked? Color.WHITE : Color.BLACK); // inverse screen color and text color
            tv_term.setTextColor(isChecked? Color.WHITE : Color.BLACK); // inverse screen color and text color
        });

        // Update EditText Description
        radio_group_status.setOnCheckedChangeListener((group, checkedId) -> { // https://developer.android.com/reference/android/widget/RadioGroup.OnCheckedChangeListener
            String status = ((RadioButton) findViewById(checkedId)).getText().toString();
            descriptionText = et_desc_text.getText().toString();
            descriptionText = descriptionText + "\nApplicant Status: " + status;
            et_desc_text.setText(descriptionText);
        });

        // Update Calender to Text Description
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> { // https://developer.android.com/reference/android/widget/CalendarView#setOnDateChangeListener(android.widget.CalendarView.OnDateChangeListener)
            // Set calender date from the argument
            calendar.set(year, month, dayOfMonth);

            // Format text to see selected date
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            String selectDate = simpleDateFormat.format(calendar.getTime());
            descriptionText = et_desc_text.getText().toString();
            descriptionText = descriptionText + "\nSelected Date: " + selectDate;
            et_desc_text.setText(descriptionText);
        });

        // Submit form using FAB
        fab_submit.setOnClickListener(view -> {
            if (checkBoxTerm.isChecked()) {
                String applicantName = et_name_text.getText().toString();
                Toast.makeText(MainActivity2.this, "Form submitted for applicant " + applicantName, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity2.this, "Please agree to the Terms and Condition checkbox before continue", Toast.LENGTH_LONG).show();
            }
        });
    }
}