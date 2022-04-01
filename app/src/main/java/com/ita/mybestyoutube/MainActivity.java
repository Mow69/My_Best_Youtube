package com.ita.mybestyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recupère le contexte
        context = getApplicationContext();
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