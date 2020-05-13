package com.himeshnayak.healapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                int checked;
                SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
                checked = prefs.getInt("DontShow", 1);

                Intent intent;

                if (checked == 1)
                    intent = new Intent(MainActivity.this, DetailAbout.class);
                else
                    intent = new Intent(MainActivity.this, IndexPage.class);

                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}
