package com.ita.mybestyoutube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class YoutubeVideoDAO extends DAO {

    public YoutubeVideoDAO(Context context) { super(new YoutubeVideoDBHelper(context)); }

    public YoutubeVideo find(Long id) {
        YoutubeVideo youtubeVideo = null;

        // ouvre la bdd
        open();

        // récupère un cursor après avoir exécuter la requete select
        Cursor cursor = db.rawQuery("select * from " + YoutubeVideoDBHelper.YOUTUBEVIDEO_TABLE_NAME +
                        " where " + YoutubeVideoDBHelper.YOUTUBEVIDEO_KEY + " = ?",
                new String[] { String.valueOf(id)});

        // positionne le cursor sur le premier enregistrement
        if (cursor != null && cursor.moveToFirst()) {
            // créé l'objet YoutubeVideo avec les données qui sont dans la bdd
            youtubeVideo = new YoutubeVideo();
            youtubeVideo.setId(cursor.getLong(YoutubeVideoDBHelper.YOUTUBEVIDEO_KEY_COLUMN_INDEX));
            youtubeVideo.setTitre(cursor.getString(YoutubeVideoDBHelper.YOUTUBEVIDEO_TITRE_COLUMN_INDEX));
            youtubeVideo.setDescription(cursor.getString(YoutubeVideoDBHelper.YOUTUBEVIDEO_DESCRIPTION_COLUMN_INDEX));
            youtubeVideo.setUrl(cursor.getString(YoutubeVideoDBHelper.YOUTUBEVIDEO_URL_COLUMN_INDEX));
            youtubeVideo.setCategorie(cursor.getString(YoutubeVideoDBHelper.YOUTUBEVIDEO_CATEGORIE_COLUMN_INDEX));


            // ferme le cursor
            cursor.close();
        }

        // ferme le cursor
        close();

        // renvoie le youtubeVideo
        return youtubeVideo;
    }

    public List<YoutubeVideo> list() {
        // créé la liste
        List<YoutubeVideo> youtubeVideos = new ArrayList<>();

        open();

        Cursor cursor = db.rawQuery("select * from " + YoutubeVideoDBHelper.YOUTUBEVIDEO_TABLE_NAME,null);

        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                // boucle tant que il y a des enregistrements dans le cursor
                YoutubeVideo youtubeVideo = new YoutubeVideo();
                youtubeVideo.setId(cursor.getLong(YoutubeVideoDBHelper.YOUTUBEVIDEO_KEY_COLUMN_INDEX));
                youtubeVideo.setTitre(cursor.getString(YoutubeVideoDBHelper.YOUTUBEVIDEO_TITRE_COLUMN_INDEX));
                youtubeVideo.setDescription(cursor.getString(YoutubeVideoDBHelper.YOUTUBEVIDEO_DESCRIPTION_COLUMN_INDEX));
                youtubeVideo.setUrl(cursor.getString(YoutubeVideoDBHelper.YOUTUBEVIDEO_URL_COLUMN_INDEX));
                youtubeVideo.setCategorie(cursor.getString(YoutubeVideoDBHelper.YOUTUBEVIDEO_CATEGORIE_COLUMN_INDEX));

                youtubeVideos.add(youtubeVideo);

                // passe à l'enregistrement suivant
                cursor.moveToNext();
            }

            cursor.close();
        }

        close();

        return youtubeVideos;
    }

    public void add(YoutubeVideo youtubeVideo) {
        open();

        // créé l'objet ContentValues qui permet de mettre les valeurs de l'objet
        // qui seront utilisées par l'insert
        ContentValues values = new ContentValues();

        values.put(YoutubeVideoDBHelper.YOUTUBEVIDEO_TITRE, youtubeVideo.getTitre());
        values.put(YoutubeVideoDBHelper.YOUTUBEVIDEO_DESCRIPTION, youtubeVideo.getDescription());
        values.put(YoutubeVideoDBHelper.YOUTUBEVIDEO_URL, youtubeVideo.getUrl());
        values.put(YoutubeVideoDBHelper.YOUTUBEVIDEO_CATEGORIE, youtubeVideo.getCategorie());

        // récupère l'id généré par la bdd
        Long id = db.insert(YoutubeVideoDBHelper.YOUTUBEVIDEO_TABLE_NAME, null, values);

        // met à jour l'objet avec l'id généré
        youtubeVideo.setId(id);

        close();
    }

    public void update(YoutubeVideo youtubeVideo) {
        open();

        ContentValues values = new ContentValues();

        values.put(YoutubeVideoDBHelper.YOUTUBEVIDEO_TITRE, youtubeVideo.getTitre());
        values.put(YoutubeVideoDBHelper.YOUTUBEVIDEO_DESCRIPTION, youtubeVideo.getDescription());
        values.put(YoutubeVideoDBHelper.YOUTUBEVIDEO_URL, youtubeVideo.getUrl());
        values.put(YoutubeVideoDBHelper.YOUTUBEVIDEO_CATEGORIE, youtubeVideo.getCategorie());

        db.update(YoutubeVideoDBHelper.YOUTUBEVIDEO_TABLE_NAME, values, YoutubeVideoDBHelper.YOUTUBEVIDEO_KEY + " = ?",
                new String[] { String.valueOf(youtubeVideo.getId())});

        close();
    }

    public void delete(YoutubeVideo youtubeVideo) {
        open();

        db.delete(YoutubeVideoDBHelper.YOUTUBEVIDEO_TABLE_NAME, YoutubeVideoDBHelper.YOUTUBEVIDEO_KEY + " = ?",
                new String[] { String.valueOf(youtubeVideo.getId())});

        close();
    }
}
