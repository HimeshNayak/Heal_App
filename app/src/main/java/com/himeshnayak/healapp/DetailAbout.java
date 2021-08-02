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

    private int checked = 0;

    private SwitchCompat dontShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_about);

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

        dontShow = findViewById(R.id.dont_show_switch);

        SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
        checked = prefs.getInt("DontShow", 1);
        dontShow.setChecked(checked == 1);

        dontShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dontShow.isChecked())
                {
                    checked = 1;
                }
                else
                {
                    checked = 0;
                }
                storeData();
            }
        });
    }

    public void storeData() {
        SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
        editor.putInt("DontShow", checked);
        editor.apply();
        if (checked == 1)
            Toast.makeText(this, "Will be shown at the starting", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Will not be shown at the starting", Toast.LENGTH_LONG).show();
    }

}