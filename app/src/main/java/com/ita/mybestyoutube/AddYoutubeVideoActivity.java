package com.ita.mybestyoutube;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddYoutubeVideoActivity extends AppCompatActivity {

    private EditText ytvTitre;
    private EditText ytvDescription;
    private EditText ytvUrl;
    private Spinner ytvCategorie;

    private Button btnAdd;
    private Button btnCancel;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youtube_video);

        // récupère le context
        context = getApplicationContext();

        // récupère les éléments du layout
        ytvTitre = findViewById(R.id.ytvTitre);
        ytvDescription = findViewById(R.id.ytvDescription);
        ytvUrl = findViewById(R.id.ytvUrl);
        ytvCategorie = findViewById(R.id.ytvCategorie);

        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        // affiche le go back
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        // récupére l'intent qui à servi pour appeler cet Activy
        Intent intent = getIntent();

        // créé le spinner
        String[] plants = new String[]{
                "Sport",
                "Musique",
                "Comédie"
        };

        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        // Initialise un ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                context, R.layout.spinner_item,plantsList);

        // relie l'adapter avec le spinner
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        ytvCategorie.setAdapter(spinnerArrayAdapter);

        // click sur btnAdd
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // récupère le contenu de ytvTitre...
                String titre = ytvTitre.getText().toString();
                String description = ytvDescription.getText().toString();
                String url = ytvUrl.getText().toString();
                // récupère l'item
                String categorie = ytvCategorie.getSelectedItem().toString();

                // créé le YoutubeVideo
                YoutubeVideo youtubeVideo = new YoutubeVideo(titre, description, url, categorie);

                // ajoute le YoutubeVideo en BDD
                YoutubeVideoDAO youtubeVideoDAO = new YoutubeVideoDAO(getApplicationContext());
                youtubeVideoDAO.add(youtubeVideo);

                // revient à la MainActivity
                finish();
            }
        });

        // click sur btnCancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // revient à la MainActivity sans rien faire
                finish();
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        // termine l'activité lors d'un go back
        finish();
        return true;
    }
}