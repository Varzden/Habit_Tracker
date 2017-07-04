package com.example.android.habit_tracker.data;

import android.provider.BaseColumns;

/**
 * Created by Varzden on 4.7.2017..
 */

public final class HabitContract {

    private HabitContract () {}

    public static final class HabitEntry implements BaseColumns{

        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_HABIT_TITLE ="title";
        public static final String COLUMN_HABIT_DESCRIPTION = "description";
        public static final String COLUMN_WEEKLY ="weekly";
    }
}
