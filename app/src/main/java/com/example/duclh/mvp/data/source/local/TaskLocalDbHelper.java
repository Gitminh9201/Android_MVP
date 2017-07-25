package com.example.duclh.mvp.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TaskLocalDbHelper  extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tasks.db";
    private final static String COMMAND_CREATE_TASK_TABLE = "CREATE TABLE "
            + TaskLocalContract.TaskEntry.TABLE_NAME
            + " ( "
            + TaskLocalContract.TaskEntry._ID
            + " INTEGER PRIMARY KEY, "
            + TaskLocalContract.TaskEntry.COLUMN_NAME_TITLE
            + " TEXT, "
            + TaskLocalContract.TaskEntry.COLUMN_NAME_DESCRIPTION
            + " TEXT, "
            + TaskLocalContract.TaskEntry.COLUMN_NAME_FINISH
            + " INTEGER)";
    private static String COMMAND_DROP_TASK_TABLE = "DROP_TABLE " + TaskLocalContract.TaskEntry
            .TABLE_NAME;

    public TaskLocalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(COMMAND_CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(COMMAND_CREATE_TASK_TABLE);
        sqLiteDatabase.execSQL(COMMAND_DROP_TASK_TABLE);
    }
}