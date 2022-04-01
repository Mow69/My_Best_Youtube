package com.ita.mybestyoutube;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailYoutubeVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailYoutubeVideo = findViewById(R.id.tvDetailYoutubeVideo);

        // affiche le go back
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // récupère l'intent qui a appelé cette activity
        Intent intent = getIntent();
        
        YoutubeVideo youtubeVideo = (YoutubeVideo)intent.getSerializableExtra("youtubevideo");

        SpannableStringBuilder boldTitreBuilder = new SpannableStringBuilder();
        String boldTitre = "%s";
        boldTitreBuilder.append(boldTitre);
        boldTitreBuilder.setSpan(new StyleSpan(Typeface.BOLD), 0, boldTitre.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvDetailYoutubeVideo.setText(String.format(boldTitreBuilder + " \n %s \n %s \n %s \n", youtubeVideo.getTitre(), youtubeVideo.getDescription(), youtubeVideo.getUrl(), youtubeVideo.getCategorie()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        // termine l'activité lors d'un go back
        finish();
        return true;
    }
}