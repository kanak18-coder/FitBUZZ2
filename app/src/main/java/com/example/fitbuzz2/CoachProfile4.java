package com.example.fitbuzz2;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class CoachProfile4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_profile4);
        VideoView videoview = findViewById(R.id.video1);
        String videopath = "android.resource://" + getPackageName() + "/" + R.raw.video;

        Uri uri = Uri.parse(videopath);
        videoview.setVideoURI(uri);




        MediaController mediaController = new MediaController(this);
        videoview.setMediaController(mediaController);
        mediaController.setAnchorView(videoview);

    }
}