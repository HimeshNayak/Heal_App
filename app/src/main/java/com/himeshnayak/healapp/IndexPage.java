package com.himeshnayak.healapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class IndexPage extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    //View creditsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","developer.healapp@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Message to the App Developer");
                intent.putExtra(Intent.EXTRA_TEXT, "Hi Developer,");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        /*
        creditsBtn = findViewById(R.id.action_credits);
        creditsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
           */
        getMenuInflater().inflate(R.menu.index_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_credits:
                openCreditsActivity();
                return true;
            case R.id.action_about:
                openAboutActivity();
                break;
            case R.id.action_settings:
                openSettingsDialogBox();
                break;
            case R.id.action_share:
                shareAppMessage();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void openCreditsActivity(){
        Intent intent = new Intent(this, Credits.class);
        startActivity(intent);
    }

    public void openAboutActivity(){
        Intent intent = new Intent(this, DetailAbout.class);
        startActivity(intent);
    }

    public void openSettingsDialogBox()
    {
        dialogBox dialogBox = new dialogBox();
        dialogBox.show(getSupportFragmentManager(), "Settings Dialog Box");
    }

    public void shareAppMessage()
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,"Download HEAL to heal yourself.\n\n https://play.google.com/store/apps/details?id=com.himeshnayak.healapp \n\nClick on this link to download heal app from Google Play Store. Do share it with the people you care about.");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Download HEAL App");
        startActivity(Intent.createChooser(shareIntent, "Share..."));
    }

}
