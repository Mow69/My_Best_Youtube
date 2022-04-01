package com.ita.mybestyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvYoutubeVideos;
    private Context context;
    private RecyclerView rvYoutubeVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ancienne vue pour la main
        setContentView(R.layout.activity_main);

        // nouvelle vue avec le recyclerview
        setContentView(R.layout.recyclerview_main);

        // ancien avec Textview
        //tvYoutubeVideos = findViewById(R.id.tvYoutubeVideos);

        // récupère le RecyclerView
        rvYoutubeVideos = findViewById(R.id.rvYoutubeVideos);

        // récupère le context
        context = getApplicationContext();

        // créé le LayoutManager pour manager le RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvYoutubeVideos.setHasFixedSize(true);
        // lie le LayoutManager avec le RecyclerView
        rvYoutubeVideos.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        YoutubeVideoDAO youtubeVideoDAO = new YoutubeVideoDAO(context);
        List<YoutubeVideo> youtubeVideos = youtubeVideoDAO.list();

        // créé l'adapteur et l'affecte au recyclerview
        YoutubeVideoAdapter youtubeVideoAdapter = new YoutubeVideoAdapter(youtubeVideos, new YoutubeVideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(YoutubeVideo item) { // méthode qui va s"exécuter quand on click sur l'item
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("youtubevideo", item);

                startActivity(intent);
            }
        });
        rvYoutubeVideos.setAdapter(youtubeVideoAdapter);

    }

    // recupère le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addYoutubeVideo:
                // créé un objet intent
                Intent intent = new Intent(context, AddYoutubeVideoActivity.class);
                // démarre l'activity AddYoutubeVideoActivity
                startActivity(intent);
                return true;
            default: return super.onOptionsItemSelected(item);
        }    }
}