package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final int ADD_TASK_REQUEST_CODE = 1;

    private List<String> taskTitles;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("task_prefs", MODE_PRIVATE);

        Button AddTaskbtn = findViewById(R.id.AddTaskbtn);
        AddTaskbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddingActivity.class);
                startActivityForResult(intent, ADD_TASK_REQUEST_CODE);
            }
        });

        loadTasksFromPrefs();

        filterDoneTasks();

        try {
            ListView listView = findViewById(R.id.ListView);

            arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, taskTitles);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedTaskTitle = taskTitles.get(position);
                    String selectedTaskStatus = getStatus(selectedTaskTitle);

                    Intent intent = new Intent(MainActivity.this, HomeWork.class);
                    intent.putExtra("selectedTaskTitle", selectedTaskTitle);
                    intent.putExtra("selectedTaskStatus", selectedTaskStatus);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MainActivity", "Error in onCreate: " + e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Task newTask = new Task(
                    data.getStringExtra("newTaskTitle"),
                    data.getStringExtra("newTaskStatus")
            );
            addTaskToListView(newTask);

            saveTaskStatus(newTask.getTask_title(), newTask.getTask_status());
        }
    }

    private void saveTaskStatus(String taskTitle, String status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(taskTitle, status);
        editor.apply();
    }

    private String getStatus(String taskTitle) {
        return sharedPreferences.getString(taskTitle, "false");
    }

    private void addTaskToListView(Task task) {
        taskTitles.add(task.getTask_title());
        arrayAdapter.notifyDataSetChanged();
    }

    private void loadTasksFromPrefs() {
        taskTitles = new ArrayList<>();
        Set<String> taskKeys = sharedPreferences.getAll().keySet();


        for (String taskKey : taskKeys) {
            String taskStatus = sharedPreferences.getString(taskKey, "DefaultStatus");

            taskTitles.add(taskKey);
        }
    }

    private void filterDoneTasks() {
        Iterator<String> iterator = taskTitles.iterator();
        while (iterator.hasNext()) {
            String taskTitle = iterator.next();
            if (getStatus(taskTitle).equals("Done")) {
                iterator.remove();
            }
        }
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
