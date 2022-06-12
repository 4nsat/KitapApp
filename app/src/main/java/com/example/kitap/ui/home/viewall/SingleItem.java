package com.example.kitap.ui.home.viewall;

public class SingleItem {
    String sId, sName, sAuthor, sGenre, sRating, sImg;

    public SingleItem(String sId, String sName, String sAuthor, String sGenre, String sRating, String sImg) {
        this.sId = sId;
        this.sName = sName;
        this.sAuthor = sAuthor;
        this.sGenre = sGenre;
        this.sRating = sRating;
        this.sImg = sImg;
    }

    public String getsId() {
        return sId;
    }

    public String getsName() {
        return sName;
    }

    public String getsAuthor() {
        return sAuthor;
    }

    public String getsGenre() {
        return sGenre;
    }

    public String getsRating() {
        return sRating;
    }

    public String getsImg() {
        return sImg;
    }
}
