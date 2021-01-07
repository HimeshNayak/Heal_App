package com.himeshnayak.healapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextClock;
import android.widget.TextView;
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

    private TextView stepsSuryaView;
    private TextView stepsMudrasanaView;
    private TextView stepsAnulomView;

    private String suryaSteps[];
    private int suryaStepsCnt = 0;
    private String mudrasanaSteps[];
    private int mudrasanaStepsCnt = 0;
    private String anulomSteps[];
    private int anulomStepsCnt = 0;

    private Button suryaPrevBtn;
    private Button suryaNextBtn;
    private Button mudrasanaPrevBtn;
    private Button mudrasanaNextBtn;
    private Button anulomPrevBtn;
    private Button anulomNextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_exercise);

        stepsSuryaView = findViewById(R.id.steps_surya_view);
        stepsMudrasanaView = findViewById(R.id.steps_mudrasana_view);
        stepsAnulomView = findViewById(R.id.steps_anulom_view);

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

        suryaSteps = getResources().getStringArray(R.array.step_surya);
        mudrasanaSteps = getResources().getStringArray(R.array.steps_mudrasana);
        anulomSteps = getResources().getStringArray(R.array.steps_anulom);

        final Button suryaPrevBtn = findViewById(R.id.surya_previous_btn);
        suryaPrevBtn.setEnabled(false);
        final Button suryaNextBtn = findViewById(R.id.surya_next_btn);
        final Button mudrasanaPrevBtn = findViewById(R.id.mudrasana_previous_btn);
        mudrasanaPrevBtn.setEnabled(false);
        final Button mudrasanaNextBtn = findViewById(R.id.mudrasana_next_btn);
        final Button anulomPrevBtn = findViewById(R.id.anulom_previous_btn);
        anulomPrevBtn.setEnabled(false);
        final Button anulomNextBtn = findViewById(R.id.anulom_next_btn);

        startSuryaNBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseList.setVisibility(View.GONE);
                suryaNView.setVisibility(View.VISIBLE);
                stepsSuryaView.setText(suryaSteps[suryaStepsCnt]);
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
                stepsMudrasanaView.setText(mudrasanaSteps[mudrasanaStepsCnt]);
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
                stepsAnulomView.setText(anulomSteps[anulomStepsCnt]);
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

        suryaNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (suryaStepsCnt <= suryaSteps.length - 1)
                {
                    suryaNextBtn.setEnabled(true);
                    suryaStepsCnt++;
                    stepsSuryaView.setText(suryaSteps[suryaStepsCnt]);
                    speak(suryaSteps[suryaStepsCnt]);
                }
                else
                    suryaNextBtn.setEnabled(false);
                suryaPrevBtn.setEnabled(true);
            }
        });

        suryaPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (suryaStepsCnt > 0)
                {
                    suryaPrevBtn.setEnabled(true);
                    suryaStepsCnt--;
                    stepsSuryaView.setText(suryaSteps[suryaStepsCnt]);
                    speak(suryaSteps[suryaStepsCnt]);
                }
                else
                    suryaPrevBtn.setEnabled(false);
                suryaNextBtn.setEnabled(true);
            }
        });

        mudrasanaNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mudrasanaStepsCnt <= mudrasanaSteps.length - 1)
                {
                    mudrasanaNextBtn.setEnabled(true);
                    mudrasanaStepsCnt++;
                    stepsMudrasanaView.setText(mudrasanaSteps[mudrasanaStepsCnt]);
                    speak(mudrasanaSteps[mudrasanaStepsCnt]);
                }
                else
                    mudrasanaNextBtn.setEnabled(false);
                mudrasanaPrevBtn.setEnabled(true);
            }
        });

        mudrasanaPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mudrasanaStepsCnt > 0)
                {
                    mudrasanaPrevBtn.setEnabled(true);
                    mudrasanaStepsCnt--;
                    stepsMudrasanaView.setText(mudrasanaSteps[mudrasanaStepsCnt]);
                    speak(mudrasanaSteps[mudrasanaStepsCnt]);
                }
                else
                    mudrasanaPrevBtn.setEnabled(false);
                mudrasanaNextBtn.setEnabled(true);
            }
        });

        anulomNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (anulomStepsCnt <= anulomSteps.length - 1)
                {
                    anulomNextBtn.setEnabled(true);
                    anulomStepsCnt++;
                    stepsAnulomView.setText(anulomSteps[anulomStepsCnt]);
                    speak(anulomSteps[anulomStepsCnt]);
                }
                else
                    anulomNextBtn.setEnabled(false);
                anulomPrevBtn.setEnabled(true);
            }
        });

        anulomPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (anulomStepsCnt > 0)
                {
                    anulomStepsCnt--;
                    speak(anulomSteps[anulomStepsCnt]);
                    stepsAnulomView.setText(anulomSteps[anulomStepsCnt]);
                    anulomPrevBtn.setEnabled(true);
                }
                else
                    anulomPrevBtn.setEnabled(false);
                anulomNextBtn.setEnabled(true);
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
>>>>>>> a45b4530525f97c396242608367afeeb3e808592
