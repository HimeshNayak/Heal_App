package com.himeshnayak.healapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextClock;
import android.widget.Toast;

import java.util.Locale;

public class PhysicalExercise extends AppCompatActivity{

    private ScrollView exerciseList;
    private ScrollView suryaNView;
    private ScrollView mudrasanaView;
    private ScrollView anulomView;
    private Button startSuryaNBtn;
    private Button backSuryaNBtn;
    private Button startMudrasanaBtn;
    private Button backMudrasanaBtn;
    private Button startAnulomBtn;
    private Button backAnulomBtn;

    private TextToSpeech mtts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_exercise);

        final com.tomer.fadingtextview.FadingTextView stepsSuryaView = findViewById(R.id.steps_surya_view);
        final com.tomer.fadingtextview.FadingTextView stepsMudrasanaView = findViewById(R.id.steps_mudrasana_view);
        final com.tomer.fadingtextview.FadingTextView stepsAnulomView = findViewById(R.id.steps_anulom_view);

        exerciseList = findViewById(R.id.exercise_list_view);
        suryaNView = findViewById(R.id.suryan_view);
        mudrasanaView = findViewById(R.id.mudrasana_view);
        anulomView = findViewById(R.id.anulom_view);

        mtts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mtts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        startSuryaNBtn.setEnabled(true);
                        startMudrasanaBtn.setEnabled(true);
                        startAnulomBtn.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                    Toast.makeText(getApplicationContext(), "Cannot Play the audio.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        startSuryaNBtn = findViewById(R.id.start_suryan_btn);
        backSuryaNBtn = findViewById(R.id.suryan_back_btn);
        startMudrasanaBtn = findViewById(R.id.start_mudrasana_btn);
        backMudrasanaBtn = findViewById(R.id.mudrasana_back_btn);
        startAnulomBtn = findViewById(R.id.start_anulom_btn);
        backAnulomBtn = findViewById(R.id.anulom_back_btn);

        startSuryaNBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseList.setVisibility(View.GONE);
                suryaNView.setVisibility(View.VISIBLE);
                stepsSuryaView.setTexts(R.array.step_surya);
                speak(stepsSuryaView.getText().toString());
            }
        });

        backSuryaNBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseList.setVisibility(View.VISIBLE);
                suryaNView.setVisibility(View.GONE);
                stopSpeak();
            }
        });

        startMudrasanaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseList.setVisibility(View.GONE);
                mudrasanaView.setVisibility(View.VISIBLE);
                stepsMudrasanaView.setTexts(R.array.steps_mudrasana);
                speak(stepsMudrasanaView.getText().toString());
            }
        });

        backMudrasanaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseList.setVisibility(View.VISIBLE);
                mudrasanaView.setVisibility(View.GONE);
                stopSpeak();
            }
        });

        startAnulomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseList.setVisibility(View.GONE);
                anulomView.setVisibility(View.VISIBLE);
                stepsAnulomView.setTexts(R.array.steps_anulom);
                speak(stepsAnulomView.getText().toString());
            }
        });

        backAnulomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseList.setVisibility(View.VISIBLE);
                anulomView.setVisibility(View.GONE);
                stopSpeak();
            }
        });
    }

    public void speak(String text)
    {
        SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
        float pitch = prefs.getFloat("Pitch", 1.0f);
        float speed = prefs.getFloat("Speed", 1.0f);
        mtts.setPitch(pitch);
        mtts.setSpeechRate(speed);
        mtts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void stopSpeak(){
        if (mtts != null) {
            mtts.stop();
        }
    }

    @Override
    protected void onDestroy() {
        stopSpeak();
        super.onDestroy();
    }
}
