package com.ita.mybestyoutube;

import java.io.Serializable;

public class YoutubeVideo implements Serializable {
    private Long id;
    private String titre;
    private String description;
    private String url;
    private String categorie;

    public YoutubeVideo() {
    }

    public YoutubeVideo(String titre, String description, String url, String categorie) {
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "com.ita.mybestyoutube.YoutubeVideo{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}
