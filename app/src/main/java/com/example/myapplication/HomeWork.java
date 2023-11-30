package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);


        String selectedTaskTitle = getIntent().getStringExtra("selectedTaskTitle");
        String selectedTaskStatus = getIntent().getStringExtra("selectedTaskStatus");


        TextView titleTextView = findViewById(R.id.titleRes);
        TextView statusTextView = findViewById(R.id.statusRes);

        titleTextView.setText(selectedTaskTitle);
        statusTextView.setText(selectedTaskStatus);
    }
}
