package com.example.kitap.ui.home.books;

public class bBooksItem {
    String bId, bName, bAuthor, bImg, bGenreId, bRating, bLink, bDescId, bDate;

    public bBooksItem(String bId, String bName, String bAuthor, String bImg, String bGenreId, String bRating, String bLink, String bDescId, String bDate) {
        this.bId = bId;
        this.bName = bName;
        this.bAuthor = bAuthor;
        this.bImg = bImg;
        this.bGenreId = bGenreId;
        this.bRating = bRating;
        this.bLink = bLink;
        this.bDescId = bDescId;
        this.bDate = bDate;
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

    public String getbGenreId() {
        return bGenreId;
    }

    public String getbRating() {
        return bRating;
    }

    public String getbLink() {
        return bLink;
    }

    public String getbDescId() {
        return bDescId;
    }

    public String getbDate() {
        return bDate;
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

    public void setbGenreId(String bGenreId) {
        this.bGenreId = bGenreId;
    }

    public void setbRating(String bRating) {
        this.bRating = bRating;
    }

    public void setbLink(String bLink) {
        this.bLink = bLink;
    }

    public void setbDescId(String bDescId) {
        this.bDescId = bDescId;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }
}
