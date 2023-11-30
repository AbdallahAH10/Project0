package com.example.myapplication;

public class Task {
    private int Task_num;
    private String Task_title;
    private String Task_status;

    public Task(String task_title, String task_status) {
        Task_title = task_title;
        Task_status = task_status;
    }
    public Task(){}

    public String getTask_title() {
        return Task_title;
    }

    public void setTask_title(String task_title) {
        Task_title = task_title;
    }

    public String getTask_status() {
        return Task_status;
    }

    public void setTask_status(String task_status) {
        Task_status = task_status;
    }
}
