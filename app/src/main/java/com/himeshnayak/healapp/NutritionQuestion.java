package com.himeshnayak.healapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class NutritionQuestion extends AppCompatActivity {

    private LinearLayout pageBefore;

    private TextView description;
    private TextView questionView;

    private Button moveQuestions;
    private Button goBack;
    private Button nextBtn;
    private Button resetBtn;

    private int position;

    private RadioGroup answerGrp;
    private RadioButton rbutton1;
    private RadioButton rbutton2;
    private RadioButton rbutton3;
    private int radioId;

    private int marks;

    final Handler handler = new Handler(Looper.myLooper());

    final private String[] questions = {
            "I eat food for my nutrition and not just for taste.",
            "Do I take regular vegetables and fruits in my diet?",
            "Do I take a balanced diet?",
            "Do I chew each mouthful slowly and thoroughly?",
            "I eat till only when my stomach is full.",
            "Do I practice fasting?",
            "Do I pray to God before eating?"
    };

    pl.droidsonroids.gif.GifImageView resultGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_question);

        pageBefore = findViewById(R.id.page_before_questions);
        description = findViewById(R.id.nutrition_description);
        moveQuestions = findViewById(R.id.move_questions);
        goBack = findViewById(R.id.come_back);

        moveQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageBefore.setVisibility(View.GONE);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        position = 0;
        marks = 0;
        questionView = findViewById(R.id.nutrition_questions_view);
        questionView.setText(questions[position]);

        answerGrp = findViewById(R.id.answer_grp);
        rbutton1 = findViewById(R.id.answer_one);
        rbutton2 = findViewById(R.id.answer_two);
        rbutton3 = findViewById(R.id.answer_three);

        resultGif = findViewById(R.id.resultgif);

        nextBtn = findViewById(R.id.question_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextBtn.setEnabled(false);
                resetBtn.setEnabled(false);

                if (radioId == R.id.answer_one)
                {
                    marks += 1;
                }
                else if (radioId == R.id.answer_two)
                {
                    marks += 2;
                }
                else if (radioId == R.id.answer_three)
                {
                    marks += 3;
                }

                if (position == questions.length - 1)
                {
                    if (marks >= 7 && marks < 12) {
                        description.setText(R.string.nutrition_ques_ans_1);
                    } else if (marks >= 12 && marks < 17) {
                        description.setText(R.string.nutrition_ques_ans_2);
                    } else
                        description.setText(R.string.nutrition_ques_ans_3);

                    description.setVisibility(View.GONE);
                    goBack.setVisibility(View.GONE);
                    pageBefore.setVisibility(View.VISIBLE);
                    resultGif.setVisibility(View.VISIBLE);
                    moveQuestions.setVisibility(View.GONE);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            resultGif.setVisibility(View.GONE);
                            description.setVisibility(View.VISIBLE);
                            goBack.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                }
                else {
                    position += 1;
                    questionView.setText(questions[position]);
                }

                rbutton1.setEnabled(true);
                rbutton2.setEnabled(true);
                rbutton3.setEnabled(true);
                answerGrp.clearCheck();

            }
        });

        resetBtn = findViewById(R.id.question_reset_btn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbutton1.setEnabled(true);
                rbutton2.setEnabled(true);
                rbutton3.setEnabled(true);
                if (radioId == rbutton1.getId())
                    marks -= 1;
                else if (radioId == rbutton2.getId())
                    marks -= 2;
                else if (radioId == rbutton3.getId())
                    marks -= 3;

                nextBtn.setEnabled(false);
                resetBtn.setEnabled(false);
                answerGrp.clearCheck();
            }
        });

        nextBtn.setEnabled(false);
        resetBtn.setEnabled(false);
    }


    public void markedAnswer(View view)
    {
        radioId = answerGrp.getCheckedRadioButtonId();

        rbutton3 = findViewById(radioId);

        if (radioId == R.id.answer_one)
        {
            rbutton1 = findViewById(R.id.answer_three);
            rbutton2 = findViewById(R.id.answer_two);
        }
        else if (radioId == R.id.answer_two)
        {
            rbutton1 = findViewById(R.id.answer_three);
            rbutton2 = findViewById(R.id.answer_one);
        }
        else if (radioId == R.id.answer_three)
        {
            rbutton1 = findViewById(R.id.answer_one);
            rbutton2 = findViewById(R.id.answer_two);
        }

        if (position == questions.length - 1) {
            String text = "Finish";
            nextBtn.setText(text);
        }

        rbutton1.setEnabled(false);
        rbutton2.setEnabled(false);

        nextBtn.setEnabled(true);
        resetBtn.setEnabled(true);

    }
}
