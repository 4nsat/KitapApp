package com.example.kitap.ui.home;

public class BooksItem {
    String bId, bName, bAuthor, bImg;
    String bGenreId, bRating, bLink, bDescId, bDate;

    public BooksItem(String bId, String bName, String bAuthor, String bImg) {
        this.bId = bId;
        this.bName = bName;
        this.bAuthor = bAuthor;
        this.bImg = bImg;
    }

    public String getbId() {
        return bId;
    }

    public String getbName() {
        return bName;
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public String getbImg() {
        return bImg;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }

    public void setbImg(String bImg) {
        this.bImg = bImg;
    }
}
