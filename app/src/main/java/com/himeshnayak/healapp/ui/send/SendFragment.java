package com.himeshnayak.healapp.ui.send;

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
import com.himeshnayak.healapp.HygieneInfo;
import com.himeshnayak.healapp.HygieneQuestion;
import com.himeshnayak.healapp.R;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);

        ImageView infoImage = root.findViewById(R.id.hygiene_info);
        infoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HygieneInfo.class);
                startActivity(intent);
            }
        });

//        ImageView gameImage = root.findViewById(R.id.hygiene_game);
//        gameImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Credits.class);
//                startActivity(intent);
//            }
//        });

        ImageView quizImage = root.findViewById(R.id.hygiene_quiz);
        quizImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HygieneQuestion.class);
                startActivity(intent);
            }
        });

        return root;
    }
}