package com.example.duclh.mvp.data.source.local;

import android.provider.BaseColumns;

public class TaskLocalContract {
    public static class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_FINISH = "finish";
    }
}
