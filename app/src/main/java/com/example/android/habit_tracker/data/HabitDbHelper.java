package com.example.android.habit_tracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.habit_tracker.data.HabitContract.HabitEntry;

/**
 * Created by Varzden on 4.7.2017..
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    // Name of database file

    private static final String DATABASE_NAME = "habits.db";

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Constructs a new instance of HabitDbHelper

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);    }

    // This is called when the database is created for the first time.

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the habits table
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE" + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_TITLE + "TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_DESCRIPTION + "TEXT, "
                + HabitEntry.COLUMN_WEEKLY + "INTEGER NOT NULL DEFAULT 0);";
        Log.v(LOG_TAG, SQL_CREATE_HABITS_TABLE);
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    // This is called when the database needs to be upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
