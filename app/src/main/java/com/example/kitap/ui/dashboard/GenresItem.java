package com.example.kitap.ui.dashboard;

public class GenresItem {
    String gId, genre, genreIc;

    public GenresItem(String gId, String genre, String genreIc) {
        this.gId = gId;
        this.genre = genre;
        this.genreIc = genreIc;
    }

    public String getgId() {
        return gId;
    }

    public void setgId(String gId) {
        this.gId = gId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenreIc() {
        return genreIc;
    }

    public void setGenreIc(String genreIc) {
        this.genreIc = genreIc;
    }
}
