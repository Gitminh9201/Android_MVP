package com.example.duclh.mvp.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.duclh.mvp.data.source.local.TaskLocalContract;

/**
 * Created by duclh on 25/07/2017.
 */

public class Task {
    private int mId;
    private String mName;
    private String mMessage;
    private boolean mIsFinish;

    public Task(int id, String name, String message, boolean isFinish) {
        mId = id;
        mName = name;
        mMessage = message;
        mIsFinish = isFinish;
    }

    public Task(String name, String message) {
        mName = name;
        mMessage = message;
        mIsFinish = false;
    }

    public boolean isFinish() {
        return mIsFinish;
    }

    public void setFinish(boolean finish) {
        mIsFinish = finish;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}