package com.himeshnayak.healapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;

public class PsychoMusic extends AppCompatActivity {

    public int currentSong;

    public ImageView playBtn;

    public ImageView stopBtn;

    public ImageView nextBtn;

    private MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psycho_music);

        song = MediaPlayer.create(this, R.raw.medmusic);
        currentSong = 1;

        playBtn = findViewById(R.id.psycho_music_play_pause);
        stopBtn = findViewById(R.id.psycho_music_stop);
        nextBtn = findViewById(R.id.psycho_music_next);

        final ImageView songOneBtn = findViewById(R.id.song_oneBtn);
        final ImageView songTwoBtn = findViewById(R.id.song_twoBtn);
        final ImageView songThreeBtn = findViewById(R.id.song_threeBtn);

        songOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSong = 1;
                playSong();
                songOneBtn.setBackgroundColor(getResources().getColor(R.color.colorLight));
                songTwoBtn.setBackgroundColor(Color.TRANSPARENT);
                songThreeBtn.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        songTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSong = 2;
                playSong();
                songTwoBtn.setBackgroundColor(getResources().getColor(R.color.colorLight));
                songOneBtn.setBackgroundColor(Color.TRANSPARENT);
                songThreeBtn.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        songThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSong = 3;
                playSong();
                songThreeBtn.setBackgroundColor(getResources().getColor(R.color.colorLight));
                songTwoBtn.setBackgroundColor(Color.TRANSPARENT);
                songOneBtn.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  playSong();
                switch(currentSong)
                {
                    case 1 : songOneBtn.setBackgroundColor(getResources().getColor(R.color.colorLight));
                        break;
                    case 2 : songTwoBtn.setBackgroundColor(getResources().getColor(R.color.colorLight));
                        break;
                    case 3 : songThreeBtn.setBackgroundColor(getResources().getColor(R.color.colorLight));
                        break;
                }
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  song.pause();
                  song.seekTo(0);
                  playBtn.setImageResource(R.drawable.ic_play);
                switch(currentSong)
                {
                    case 1 : songOneBtn.setBackgroundColor(Color.TRANSPARENT);
                        break;
                    case 2 : songTwoBtn.setBackgroundColor(Color.TRANSPARENT);
                        break;
                    case 3 : songThreeBtn.setBackgroundColor(Color.TRANSPARENT);
                        break;
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                   song.pause();
                   song.seekTo(0);
                switch(currentSong)
                {
                    case 1 : song = MediaPlayer.create(getApplicationContext(), R.raw.omchant);
                        currentSong = 2;
                        songOneBtn.setBackgroundColor(Color.TRANSPARENT);
                        songTwoBtn.setBackgroundColor(getResources().getColor(R.color.colorLight));
                        break;
                    case 2 : song = MediaPlayer.create(getApplicationContext(), R.raw.watermed);
                        currentSong = 3;
                        songTwoBtn.setBackgroundColor(Color.TRANSPARENT);
                        songThreeBtn.setBackgroundColor(getResources().getColor(R.color.colorLight));
                        break;
                    case 3 : song = MediaPlayer.create(getApplicationContext(), R.raw.medmusic);
                        currentSong = 1;
                        songThreeBtn.setBackgroundColor(Color.TRANSPARENT);
                        songOneBtn.setBackgroundColor(getResources().getColor(R.color.colorLight));
                        break;
                }
                song.start();
                song.setLooping(true);
                playBtn.setImageResource(R.drawable.ic_pause);
            }
        });

    }

    public void playSong()
    {

        if (song.isPlaying())
        {
            song.pause();
            playBtn.setImageResource(R.drawable.ic_play);
        }
        else
        {
            switch(currentSong)
            {
                case 1 : song = MediaPlayer.create(getApplicationContext(), R.raw.medmusic);
                    break;
                case 2 : song = MediaPlayer.create(getApplicationContext(), R.raw.omchant);
                    break;
                case 3 : song = MediaPlayer.create(getApplicationContext(), R.raw.watermed);
                    break;
            }
            song.start();
            song.setLooping(true);
            playBtn.setImageResource(R.drawable.ic_pause);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        song.stop();
    }
}
