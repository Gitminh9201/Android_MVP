package com.example.duclh.mvp.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.duclh.mvp.BR;
import com.example.duclh.mvp.data.source.local.TaskLocalContract;

/**
 * Created by duclh on 25/07/2017.
 */

public class Task extends BaseObservable{
    private int mId;
    private String mName;
    private String mMessage;
    private boolean mIsFinish;

    public Task() {
    }

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

    @Bindable
    public boolean isFinish() {
        return mIsFinish;
    }

    public void setFinish(boolean finish) {
        mIsFinish = finish;
        notifyPropertyChanged(BR.finish);
    }

    @Bindable
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
        notifyPropertyChanged(BR.message);
    }
}