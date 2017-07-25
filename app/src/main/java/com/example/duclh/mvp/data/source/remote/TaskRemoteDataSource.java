package com.example.duclh.mvp.data.source.remote;

import com.example.duclh.mvp.data.model.Task;
import com.example.duclh.mvp.data.source.TaskDataSource;

/**
 * Created by duclh on 25/07/2017.
 */

public class TaskRemoteDataSource implements TaskDataSource {
    @Override
    public void addTask(Task task, Callback<Integer> callback) {
    }

    @Override
    public void deleteTask(int id, Callback<Integer> callback) {
    }

    @Override
    public void updateTask(int id, String title, String message,
                           Callback<Integer> callback) {
    }

    @Override
    public void getTaskById(int id, Callback<Task> callback) {
    }

    @Override
    public void getTasks(Callbacks<Task> getTasksCallback) {
    }

    @Override
    public void finishTask(int id, boolean isFinish, Callback<Integer> callback) {
    }
}
