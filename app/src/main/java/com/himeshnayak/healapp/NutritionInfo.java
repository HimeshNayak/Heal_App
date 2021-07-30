package com.himeshnayak.healapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class NutritionInfo extends AppCompatActivity {

    private TextToSpeech mTTS;
    private ImageView playPauseBtn;
    private TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_info);

        infoText = findViewById(R.id.info_text);

        playPauseBtn = findViewById(R.id.play_info);
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        playPauseBtn.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                    Toast.makeText(getApplicationContext(), "Cannot Play the audio.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        playPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTTS.isSpeaking())
                {
                    speak();
                    playPauseBtn.setImageResource(R.drawable.ic_pause);
                }
                else
                {
                    mTTS.stop();
                    playPauseBtn.setImageResource(R.drawable.ic_play);
                }
            }
        });

    }

    private void speak() {
        String text = infoText.getText().toString();
        SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
        float pitch = prefs.getFloat("Pitch", 1.0f);
        float speed = prefs.getFloat("Speed", 1.0f);
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, text);
    }
    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}
