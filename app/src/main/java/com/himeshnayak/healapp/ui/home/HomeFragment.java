package com.himeshnayak.healapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.himeshnayak.healapp.HygieneInfo;
import com.himeshnayak.healapp.NutritionInfo;
import com.himeshnayak.healapp.PhysicalInfo;
import com.himeshnayak.healapp.PsychoInfo;
import com.himeshnayak.healapp.R;
import com.himeshnayak.healapp.SleepInfo;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        TextView physicalIndex = root.findViewById(R.id.physical_index);
        physicalIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PhysicalInfo.class);
                startActivity(intent);
            }
        });

        TextView psychoIndex = root.findViewById(R.id.psycho_index);
        psychoIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PsychoInfo.class);
                startActivity(intent);
            }
        });

        TextView nutritionIndex = root.findViewById(R.id.nutrition_index);
        nutritionIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NutritionInfo.class);
                startActivity(intent);
            }
        });

        TextView sleepIndex = root.findViewById(R.id.sleep_index);
        sleepIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SleepInfo.class);
                startActivity(intent);
            }
        });

        TextView hygieneIndex = root.findViewById(R.id.hygiene_index);
        hygieneIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HygieneInfo.class);
                startActivity(intent);
            }
        });

        return root;
    }
}