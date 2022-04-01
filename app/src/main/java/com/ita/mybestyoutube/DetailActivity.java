package com.ita.mybestyoutube;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailYoutubeVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailYoutubeVideo = findViewById(R.id.tvDetailYoutubeVideo);

        // récupère l'intent qui a appelé cette activity
        Intent intent = getIntent();
        
        YoutubeVideo youtubeVideo = (YoutubeVideo)intent.getSerializableExtra("youtubevideo");

        tvDetailYoutubeVideo.setText(String.format("%d - %s - %s - %s - %s", youtubeVideo.getId(), youtubeVideo.getTitre(), youtubeVideo.getDescription(), youtubeVideo.getUrl(), youtubeVideo.getCategorie()));
    }
}