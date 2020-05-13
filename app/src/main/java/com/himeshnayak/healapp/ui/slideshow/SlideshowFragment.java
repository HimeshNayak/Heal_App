package com.himeshnayak.healapp.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.himeshnayak.healapp.Credits;
import com.himeshnayak.healapp.PhysicalAlarm;
import com.himeshnayak.healapp.PhysicalExercise;
import com.himeshnayak.healapp.PhysicalInfo;
import com.himeshnayak.healapp.PhysicalQuestion;
import com.himeshnayak.healapp.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        ImageView infoImage = root.findViewById(R.id.physical_info);
        infoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PhysicalInfo.class);
                startActivity(intent);
            }
        });

        ImageView alarmImage = root.findViewById(R.id.physical_alarm);
        alarmImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PhysicalAlarm.class);
                startActivity(intent);
            }
        });

        ImageView exerciseImage = root.findViewById(R.id.physical_exercise);
        exerciseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PhysicalExercise.class);
                startActivity(intent);
            }
        });

        ImageView quizImage = root.findViewById(R.id.physical_quiz);
        quizImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PhysicalQuestion.class);
                startActivity(intent);
            }
        });

        return root;
    }
}