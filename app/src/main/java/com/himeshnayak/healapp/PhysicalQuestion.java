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

public class PhysicalQuestion extends AppCompatActivity {

    private LinearLayout pageBefore;

    private TextView physicalDescription;
    private TextView questionView;

    private Button moveQuestions;
    private Button goBack;
    private Button nextBtn;
    private Button resetBtn;

    private int position;

    private RadioGroup physicalAnswerGrp;
    private RadioButton rbutton1;
    private RadioButton rbutton2;
    private RadioButton rbutton3;
    private int radioId;

    private int marks;

    final Handler handler = new Handler(Looper.myLooper());
    pl.droidsonroids.gif.GifImageView resultGif;

    final private String[] questions = {
            "Do I feel hungry at appropriate timings i.e, breakfast, lunch and dinner?",
            "Do I have a glow in my skin?",
            "Do I feel energetic everyday?",
            "Do I spring out of bed each morning?",
            "Do I feel a chronic headache?",
            "Do I exercise / workout everyday?",
            "Do I have clean bowel movement daily?",
            "Do I have clear sparkling eyes? (Check the white part of your eyes)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_question);

        pageBefore = findViewById(R.id.page_before_questions);
        physicalDescription = findViewById(R.id.physical_description);
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
        questionView = findViewById(R.id.physical_questions_view);
        questionView.setText(questions[position]);

        physicalAnswerGrp = findViewById(R.id.answer_grp);
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

                if(position == questions.length - 1)
                {
                    if(marks >= 8 && marks < 13)
                    {
                        physicalDescription.setText(R.string.physical_ques_ans_1);
                    }
                    else if (marks >= 13 && marks < 18){
                        physicalDescription.setText(R.string.physical_ques_ans_2);
                    }
                    else
                        physicalDescription.setText(R.string.physical_ques_ans_3);

                    physicalDescription.setVisibility(View.GONE);
                    goBack.setVisibility(View.GONE);
                    pageBefore.setVisibility(View.VISIBLE);
                    resultGif.setVisibility(View.VISIBLE);
                    moveQuestions.setVisibility(View.GONE);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            resultGif.setVisibility(View.GONE);
                            physicalDescription.setVisibility(View.VISIBLE);
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
                physicalAnswerGrp.clearCheck();

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
                physicalAnswerGrp.clearCheck();
            }
        });

        nextBtn.setEnabled(false);
        resetBtn.setEnabled(false);
    }


    public void markedAnswer(View view)
    {
        radioId = physicalAnswerGrp.getCheckedRadioButtonId();

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

        if (position == questions.length - 1)
            nextBtn.setText(getText(R.string.finish));

        rbutton1.setEnabled(false);
        rbutton2.setEnabled(false);

        nextBtn.setEnabled(true);
        resetBtn.setEnabled(true);

    }
}