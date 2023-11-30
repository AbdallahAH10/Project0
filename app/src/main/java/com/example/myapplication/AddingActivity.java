package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        EditText titleAdd = findViewById(R.id.titleAdd);
        EditText statusAdd = findViewById(R.id.statusAdd);
        Button addingBtn = findViewById(R.id.Addingbtn);

        addingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTaskTitle = titleAdd.getText().toString();
                String newTaskStatus = statusAdd.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("newTaskTitle", newTaskTitle);
                resultIntent.putExtra("newTaskStatus", newTaskStatus);
                setResult(Activity.RESULT_OK, resultIntent);

                finish();
            }
        });

        }
    }
