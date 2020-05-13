package com.himeshnayak.healapp.ui.share;

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
import com.himeshnayak.healapp.R;
import com.himeshnayak.healapp.SleepAlarm;
import com.himeshnayak.healapp.SleepInfo;
import com.himeshnayak.healapp.SleepQuestion;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);

        ImageView infoImage = root.findViewById(R.id.sleep_info);
        infoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SleepInfo.class);
                startActivity(intent);
            }
        });

        ImageView restrictImage = root.findViewById(R.id.sleep_quiz);
        restrictImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SleepQuestion.class);
                startActivity(intent);
            }
        });

//        ImageView alarmImage = root.findViewById(R.id.sleep_alarm);
//        alarmImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Credits.class);
//                startActivity(intent);
//            }
//        });

        ImageView musicImage = root.findViewById(R.id.sleep_music);
        musicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SleepAlarm.class); //SleepAlarm class is SleepMusic
                startActivity(intent);
            }
        });

        return root;
    }
}