package com.himeshnayak.healapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SleepAlarm extends AppCompatActivity {

    private MediaPlayer playSleepMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_alarm);

        playSleepMusic = MediaPlayer.create(this, R.raw.yognidra);

        final ImageView sleepMusicBtn = findViewById(R.id.sleep_music_play_pause);
        sleepMusicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playSleepMusic.isPlaying())
                {
                    playSleepMusic.pause();
                    sleepMusicBtn.setImageResource(R.drawable.ic_play);
                }
                else
                {
                    playSleepMusic.start();
                    sleepMusicBtn.setImageResource(R.drawable.ic_pause);
                }

            }
        });

        ImageView sleepMusicStopBtn = findViewById(R.id.sleep_music_stop);
        sleepMusicStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playSleepMusic.isPlaying())
                {
                    playSleepMusic.pause();
                    playSleepMusic.seekTo(0);
                    sleepMusicBtn.setImageResource(R.drawable.ic_play);
                }
            }
        });

        ImageView nextBtn = findViewById(R.id.sleep_music_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SleepAlarm.this, "No Other Song available right now.", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        playSleepMusic.stop();
    }
}
