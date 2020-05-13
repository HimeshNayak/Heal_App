package com.himeshnayak.healapp.ui.gallery;

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
import com.himeshnayak.healapp.PsychoInfo;
import com.himeshnayak.healapp.PsychoMusic;
import com.himeshnayak.healapp.PsychoQuestion;
import com.himeshnayak.healapp.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        ImageView quizImage = root.findViewById(R.id.psycho_quiz);
        quizImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PsychoQuestion.class);
                startActivity(intent);
            }
        });

        ImageView infoImage = root.findViewById(R.id.psycho_info);
        infoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PsychoInfo.class);
                startActivity(intent);
            }
        });

//        ImageView gameImage = root.findViewById(R.id.psycho_game);
//        gameImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Credits.class);
//                startActivity(intent);
//            }
//        });

        ImageView mediImage = root.findViewById(R.id.psycho_medi);
        mediImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PsychoMusic.class);
                startActivity(intent);
            }
        });

        return root;
    }
}