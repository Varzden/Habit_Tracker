package com.example.android.habit_tracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habit_tracker.data.HabitContract.HabitEntry;
import com.example.android.habit_tracker.data.HabitDbHelper;

/**
 * Created by Varzden on 4.7.2017..
 */

public class EditorActivity extends AppCompatActivity {

    private EditText mTitleET;
    private EditText mDescriptionET;
    private EditText mTimesAWeekET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor);

        mTitleET = (EditText) findViewById(R.id.edit_habit_name);
        mDescriptionET = (EditText) findViewById(R.id.edit_description);
        mTimesAWeekET = (EditText) findViewById(R.id.edit_times_a_week);

    }

    private void insertHabit() {
        String titleString = mTitleET.getText().toString().trim();
        String descriptionString = mDescriptionET.getText().toString().trim();
        String timesAWeekString = mTimesAWeekET.getText().toString().trim();
        int timesAWeek = Integer.parseInt(timesAWeekString);

        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_TITLE, titleString);
        values.put(HabitEntry.COLUMN_HABIT_DESCRIPTION, descriptionString);
        values.put(HabitEntry.COLUMN_WEEKLY, timesAWeek);

        // Insert a new row for habit in the database, returning the ID of that new row.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving habit", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Habit saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save habit to database
                insertHabit();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
