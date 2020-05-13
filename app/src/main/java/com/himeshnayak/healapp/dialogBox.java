package com.himeshnayak.healapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import static android.content.Context.MODE_PRIVATE;

public class dialogBox extends DialogFragment {

    private float pitch;
    private float speed;
    private SeekBar mSeekBarPitch;
    private SeekBar mSeekBarSpeed;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.action_settings, null);

        mSeekBarPitch = view.findViewById(R.id.pitch_seekBar);
        mSeekBarSpeed = view.findViewById(R.id.speed_seekBar);

        SharedPreferences prefs = getContext().getSharedPreferences("PreferencesName", MODE_PRIVATE);
        pitch = prefs.getFloat("Pitch", 0.5f);
        speed = prefs.getFloat("Speed", 0.5f);

        mSeekBarPitch.setProgress((int)(50));
        mSeekBarSpeed.setProgress((int)(50));

        builder.setView(view)
                .setTitle("Settings")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pitch = (float) mSeekBarPitch.getProgress() / 50;
                        if (pitch < 0.1)
                            pitch = 0.1f;
                        speed = (float) mSeekBarSpeed.getProgress() / 50;
                        if (speed < 0.1)
                            speed = 0.1f;

                        SharedPreferences.Editor editor = getContext().getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
                        editor.putFloat("Pitch", pitch);
                        editor.putFloat("Speed", speed);
                        editor.apply();
                    }
                });

        return builder.create();
    }
}
