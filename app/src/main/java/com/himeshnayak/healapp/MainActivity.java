package com.himeshnayak.healapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

//  Show the content on the page till 3 seconds and then go the index or detailAbout activity

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      new instance of Timer is created
        timer = new Timer();

//      schedules the specified task for execution after the specified delay.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

//              check if detailAbout activity is to be shown or not
//              showAbout key is set in SharedPreferences

                SharedPreferences prefs = getSharedPreferences("healAppData", MODE_PRIVATE);
                int checked = prefs.getInt("showAbout", 1);

                Intent intent;

//              value is 1 when detailAbout activity is shown
//              else any other value, index activity is shown

                if (checked == 1)
                    intent = new Intent(MainActivity.this, DetailAbout.class);
                else
                    intent = new Intent(MainActivity.this, IndexPage.class);

//              Explicit intent is used to go to the new activity
                startActivity(intent);

//              after starting the new activity this activity is finished
                finish();
            }
        }, 3000);

    }
}
