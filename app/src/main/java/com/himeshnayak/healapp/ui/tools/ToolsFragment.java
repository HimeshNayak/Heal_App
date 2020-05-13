package com.himeshnayak.healapp.ui.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.himeshnayak.healapp.Credits;
import com.himeshnayak.healapp.NutritionInfo;
import com.himeshnayak.healapp.NutritionQuestion;
import com.himeshnayak.healapp.R;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

//        ImageView gameImage = root.findViewById(R.id.nutrition_game);
//        gameImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Credits.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageView eatImage = root.findViewById(R.id.nutrition_eat);
//        eatImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Credits.class);
//                startActivity(intent);
//            }
//        });

        ImageView quizImage = root.findViewById(R.id.nutrition_quiz);
        quizImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NutritionQuestion.class);
                startActivity(intent);
            }
        });

        ImageView infoImage = root.findViewById(R.id.nutrition_info);
        infoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NutritionInfo.class);
                startActivity(intent);
            }
        });

        return root;
    }
}