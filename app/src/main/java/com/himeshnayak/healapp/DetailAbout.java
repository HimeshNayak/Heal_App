package com.himeshnayak.healapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailAbout extends AppCompatActivity {

//  Display about the app and show option to show the activity in the starting

//  var to get showAbout value from shared preferences
    private int checked = 0;

//  switch to change showAbout value
    private SwitchCompat showAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_about);

//      On tap home button go to index activity
        ImageView homeBtn;
        homeBtn = findViewById(R.id.go_home_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailAbout.this, IndexPage.class);
                startActivity(intent);
                finish();
            }
        });

        showAbout = findViewById(R.id.dont_show_switch);

        SharedPreferences prefs = getSharedPreferences("healAppData", MODE_PRIVATE);
        checked = prefs.getInt("showAbout", 1);
        showAbout.setChecked(checked == 1);

        showAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checked = (showAbout.isChecked())? 1 : 0;
                storeData();
            }
        });
    }

    public void storeData() {
//      change the value of showAbout in the shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("healAppData", MODE_PRIVATE).edit();
//      1: show activity in starting
//      0: do not show activity in starting
        editor.putInt("showAbout", checked);
        editor.apply();

//      show toast accordingly
        if (checked == 1)
            Toast.makeText(this, "Will be shown at the starting", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Will not be shown at the starting", Toast.LENGTH_LONG).show();
    }

}