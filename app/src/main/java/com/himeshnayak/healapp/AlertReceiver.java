package com.himeshnayak.healapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.core.app.NotificationCompat;

import static android.content.Context.MODE_PRIVATE;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences prefs = context.getSharedPreferences("PreferencesName", MODE_PRIVATE);
        String mantra = prefs.getString("Mantra", "We are born everyday! Make this life fruitful!!");

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb;
        nb = notificationHelper.getNotification("Motivation Mantra", mantra);
        notificationHelper.getManager().notify(1, nb.build());
    }
}
