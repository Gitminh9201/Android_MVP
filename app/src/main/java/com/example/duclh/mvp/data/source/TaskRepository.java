package com.example.duclh.mvp.data.source;


import com.example.duclh.mvp.data.model.Task;
import com.example.duclh.mvp.data.source.local.TaskLocalContract;
import com.example.duclh.mvp.data.source.local.TaskLocalDataSource;

import java.util.List;

public class TaskRepository implements TaskDataSource {
    // xu ly model
    private TaskDataSource mTaskLocalDataSource;
    private TaskDataSource mTaskRemoteDataSource;

    public TaskRepository(TaskDataSource taskLocalDataSource,
                          TaskDataSource taskRemoteDataSource) {
        mTaskLocalDataSource = taskLocalDataSource;
        mTaskRemoteDataSource = taskRemoteDataSource;
    }

    @Override
    public void addTask(Task task, TaskDataSource.Callback<Integer> callback) {
        mTaskLocalDataSource.addTask(task, callback);
        mTaskRemoteDataSource.addTask(task, callback);
    }

    @Override
    public void deleteTask(int id, TaskDataSource.Callback<Integer> callback) {
        mTaskRemoteDataSource.deleteTask(id, callback);
        mTaskLocalDataSource.deleteTask(id, callback);
    }

    @Override
    public void updateTask(int id, String title, String message, TaskDataSource.Callback<Integer>
            callback) {
        mTaskLocalDataSource.updateTask(id, title, message, callback);
        mTaskRemoteDataSource.updateTask(id, title, message, callback);
    }

    @Override
    public void getTaskById(final int id, final Callback<Task> callback) {
        mTaskLocalDataSource.getTaskById(id, new Callback<Task>() {
            @Override
            public void onSuccess(Task data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail() {
                mTaskRemoteDataSource.getTaskById(id, callback);
            }
        });
    }

    @Override
    public void getTasks(final Callbacks<Task> callbacks) {
        mTaskLocalDataSource.getTasks(new Callbacks<Task>() {
            @Override
            public void onExistedList(List<Task> data) {
                callbacks.onExistedList(data);
            }

            @Override
            public void onEmptyList() {
                mTaskRemoteDataSource.getTasks(callbacks);
            }
        });
    }

    @Override
    public void finishTask(int id, boolean isFinish, Callback<Integer> callback) {
        mTaskLocalDataSource.finishTask(id, isFinish, callback);
        mTaskRemoteDataSource.finishTask(id, isFinish, callback);
    }
}
