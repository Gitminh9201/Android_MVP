package com.example.duclh.mvp.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duclh.mvp.R;
import com.example.duclh.mvp.data.model.Task;
import com.example.duclh.mvp.data.source.TaskDataSource;

import java.util.ArrayList;
import java.util.List;


public class TaskLocalDataSource extends TaskLocalDbHelper implements TaskDataSource {
    public TaskLocalDataSource(Context context) {
        super(context);
    }

    @Override
    public void addTask(Task task, Callback<Integer> callback) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskLocalContract.TaskEntry.COLUMN_NAME_TITLE, task.getName());
        values.put(TaskLocalContract.TaskEntry.COLUMN_NAME_DESCRIPTION, task.getMessage());
        values.put(TaskLocalContract.TaskEntry.COLUMN_NAME_FINISH, 0);
        long result = db.insert(TaskLocalContract.TaskEntry.TABLE_NAME, null, values);
        if (result > 0) {
            callback.onSuccess((int) result);
        } else
            callback.onFail();
        db.close();
    }

    @Override
    public void deleteTask(int id, Callback<Integer> callback) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = TaskLocalContract.TaskEntry._ID + "=?";
        String[] whereClauseArgs = {String.valueOf(id)};
        long result = db.delete(TaskLocalContract.TaskEntry.TABLE_NAME, whereClause, whereClauseArgs);
        db.close();
        if (result > 0) {
            callback.onSuccess((int) result);
        } else callback.onFail();
    }

    @Override
    public void updateTask(int id, String title, String message, Callback<Integer> callback) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskLocalContract.TaskEntry.COLUMN_NAME_TITLE, title);
        values.put(TaskLocalContract.TaskEntry.COLUMN_NAME_DESCRIPTION, message);
        String whereClause = TaskLocalContract.TaskEntry._ID + "=?";
        String[] whereClauseArgs = {String.valueOf(id)};
        int result = db.update(TaskLocalContract.TaskEntry.TABLE_NAME, values, whereClause,
                whereClauseArgs);
        db.close();
        if (result > 0) {
            callback.onSuccess(result);
        } else callback.onFail();
    }

    @Override
    public void getTaskById(int id, Callback<Task> callback) {
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {
                TaskLocalContract.TaskEntry._ID,
                TaskLocalContract.TaskEntry.COLUMN_NAME_TITLE,
                TaskLocalContract.TaskEntry.COLUMN_NAME_DESCRIPTION,
                TaskLocalContract.TaskEntry.COLUMN_NAME_FINISH
        };
        String selection = TaskLocalContract.TaskEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor c =
                db.query(TaskLocalContract.TaskEntry.TABLE_NAME, columns, selection, selectionArgs, null,
                        null, null);
        Task task = null;
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            int taskId = c.getInt(c.getColumnIndex(TaskLocalContract.TaskEntry._ID));
            String title =
                    c.getString(c.getColumnIndex(TaskLocalContract.TaskEntry.COLUMN_NAME_TITLE));
            String description =
                    c.getString(c.getColumnIndex(TaskLocalContract.TaskEntry.COLUMN_NAME_DESCRIPTION));
            int finish = c.getInt(c.getColumnIndex(TaskLocalContract.TaskEntry.COLUMN_NAME_FINISH));
            boolean isFinish;
            isFinish = (finish != 0);
            task = new Task(taskId, title, description, isFinish);
        }
        if (c != null) {
            c.close();
        }
        db.close();
        if (task != null) {
            callback.onSuccess(task);
        } else {
            callback.onFail();
        }
    }

    @Override
    public void getTasks(Callbacks<Task> callback) {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {
                TaskLocalContract.TaskEntry._ID,
                TaskLocalContract.TaskEntry.COLUMN_NAME_TITLE,
                TaskLocalContract.TaskEntry.COLUMN_NAME_DESCRIPTION,
                TaskLocalContract.TaskEntry.COLUMN_NAME_FINISH
        };
        Cursor c =
                db.query(TaskLocalContract.TaskEntry.TABLE_NAME, columns, null, null, null, null, null,
                        null);
        if (c != null && c.moveToFirst()) {
            do {
                int taskId = c.getInt(c.getColumnIndex(TaskLocalContract.TaskEntry._ID));
                String title =
                        c.getString(c.getColumnIndex(TaskLocalContract.TaskEntry.COLUMN_NAME_TITLE));
                String description =
                        c.getString(c.getColumnIndex(TaskLocalContract.TaskEntry.COLUMN_NAME_DESCRIPTION));
                int finish = c.getInt(c.getColumnIndex(TaskLocalContract.TaskEntry.COLUMN_NAME_TITLE));
                boolean isFinish = false;
                if (finish != 0) {
                    isFinish = true;
                }
                Task task = new Task(taskId, title, description, isFinish);
                tasks.add(task);
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }
        db.close();
        if (tasks.isEmpty()) {
            callback.onEmptyList();
        } else {
            callback.onExistedList(tasks);
        }
    }

    @Override
    public void finishTask(int id, boolean isFinish, Callback<Integer> callback) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        String whereClause = TaskLocalContract.TaskEntry._ID + "=?";
        String[] whereClauseArgs = {String.valueOf(id)};
        values.put(TaskLocalContract.TaskEntry.COLUMN_NAME_FINISH, isFinish ? 1 : 0);
        int result = db.update(TaskLocalContract.TaskEntry.TABLE_NAME, values, whereClause,
                whereClauseArgs);
        db.close();
        if (result > 0) {
            callback.onSuccess(result);
        } else callback.onFail();
    }
}