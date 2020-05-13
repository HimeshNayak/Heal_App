package com.himeshnayak.healapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class PhysicalAlarm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private Button exerciseNotifyCancelBtn;
    private EditText notifyMsg;

    public static String msg;

    public Button exerciseTimeBtn;

    public String timeText = "";

    private NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_alarm);

        exerciseTimeBtn = findViewById(R.id.exercise_alarm_setBtn);
        exerciseNotifyCancelBtn = findViewById(R.id.exercise_notifyCancelBtn);

        notifyMsg = findViewById(R.id.exercise_notifyText);
        notificationHelper = new NotificationHelper(this);

        SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
        timeText = prefs.getString("Time", "Set Time");
        if (timeText == null || timeText.equals("Set Time"))
        {
            exerciseTimeBtn.setText(getText(R.string.setTime));
            exerciseTimeBtn.setEnabled(true);
            exerciseNotifyCancelBtn.setEnabled(false);
            notifyMsg.setHint(R.string.msg_moti);
            notifyMsg.setEnabled(true);
        }
        else
        {
            exerciseTimeBtn.setText(timeText);
            exerciseTimeBtn.setEnabled(false);
            exerciseNotifyCancelBtn.setEnabled(true);
            notifyMsg.setHint(prefs.getString("Mantra", getText(R.string.msg_moti).toString()));
            notifyMsg.setEnabled(false);
        }


        exerciseTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg = notifyMsg.getText().toString();
                if(msg.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter the Motivation Mantra", Toast.LENGTH_LONG).show();
                }
                else {
                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(getSupportFragmentManager(), "Time Picker");
                }

            }
        });

        exerciseNotifyCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarm();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int min) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, min);
        c.set(Calendar.SECOND, 0);

        String tText;
        tText = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        exerciseTimeBtn.setText(tText);
        timeText = hour + " : " + min;

        startAlarm(c);

        msg = notifyMsg.getText().toString();

        storeData();
        notifyMsg.setEnabled(false);

        exerciseTimeBtn.setText(timeText);
        exerciseTimeBtn.setEnabled(false);
        exerciseNotifyCancelBtn.setEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c)
    {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if(c.before(Calendar.getInstance()))
        {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void cancelAlarm()
    {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        exerciseTimeBtn.setEnabled(true);
        exerciseNotifyCancelBtn.setEnabled(false);
        exerciseTimeBtn.setText(getText(R.string.setTime));
        notifyMsg.setHint(R.string.msg_moti);
        notifyMsg.setEnabled(true);
        msg = getText(R.string.msg_moti).toString();
        timeText = getText(R.string.setTime).toString();
        storeData();
    }

    public void storeData()
    {
        SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
        editor.putString("Mantra", msg);
        editor.putString("Time", timeText);
        editor.apply();
    }

}
