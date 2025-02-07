package com.example.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    // Declare view variable
    Switch sw_remember;
    EditText et_user_name, et_password;
    Button bt_login;

    // other variable
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
        sw_remember = findViewById(R.id.sw_remember);
        et_user_name = findViewById(R.id.et_user_name);
        et_password = findViewById(R.id.et_password);
        bt_login = findViewById(R.id.bt_login);

        // Load the data when open the app
        LoadData();
    }

    // Save data onPause (when app is closed)
    protected void onStop() { // onPause is fine too
        super.onStop();
        // If sw_remember.isChecked() the do SaveData()
        if (sw_remember.isChecked()) {
            SaveData();
        }
        else {
            RemoveData();
        }
    }

    // Saving the information
    public void SaveData() {
        sp = getPreferences(MODE_PRIVATE);
        spe = sp.edit();

        //
        spe.putString("key_username", et_user_name.getText().toString());
        spe.putString("key_password", et_password.getText().toString());
        spe.putBoolean("key_remember", sw_remember.isChecked());

        // Must commit after saving data. EIther commit or apply
        // spe.commit();
        spe.apply();
    }

    // Remove the saved info
    public void RemoveData() {
        sp = getPreferences(MODE_PRIVATE);
        spe = sp.edit();

        //
        spe.remove("key_username");
        spe.remove("key_password");
        spe.remove("key_remember");
        spe.commit();
    }

    // Loading the information
    public void LoadData() {
        sp = getPreferences(MODE_PRIVATE);
        et_user_name.setText(sp.getString("key_username", null));
        et_password.setText(sp.getString("key_password", null));
        sw_remember.setChecked(sp.getBoolean("key_remember",false));

    }
}