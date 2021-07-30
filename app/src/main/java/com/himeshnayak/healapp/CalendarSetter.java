package com.himeshnayak.healapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarSetter extends AppCompatActivity {

    private String selectedDate;
    private SQLiteDatabase sqLiteDatabase;
    private TextView eventText;
    private EditText exerciseDoneContent;
    private CalendarView calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_setter);

        Button exerciseDoneBtn;

        eventText = findViewById(R.id.eventText);
        exerciseDoneContent = findViewById(R.id.exercise_name);
        exerciseDoneBtn = findViewById(R.id.exercise_done_btn);

        calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int day, int month, int year) {
                selectedDate = day + Integer.toString(month) + year;
                calendar.setWeekDayTextAppearance(R.style.selectedWeek);
                readData(calendarView);
            }
        });

        exerciseDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData(view);
            }
        });

        try {
            SQLiteHander sqLiteHander = new SQLiteHander(this, "CalendarDatabase", null, 1);
            sqLiteDatabase = sqLiteHander.getWritableDatabase();
            sqLiteDatabase.execSQL("CREATE TABLE Calendar(DATE TEXT, EVENT TEXT);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertData(View view)
    {
        if (exerciseDoneContent != null)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put("Date", selectedDate);
            contentValues.put("Event", exerciseDoneContent.getText().toString());
            sqLiteDatabase.insert("CalendarDatabase", null, contentValues);
            Toast.makeText(this, "Written in DB", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Enter the exercise you did", Toast.LENGTH_SHORT).show();

    }

    public void readData(View view)
    {
        String sqlQuery = "SELECT EVENT FROM CalendarDatabase where DATE =" + selectedDate;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
            cursor.moveToFirst();
            eventText.setText(cursor.getString(0));
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            String setSelectedData = "Read nothing " + selectedDate;
            eventText.setText(setSelectedData);
        }

    }
}
