package com.ssaczkowski.mynotes;

public class Note {

    private String title;
    private String content;
    private boolean isFavorite;
    private int color;

    public Note(String title, String content, boolean isFavorite, int color) {
        this.title = title;
        this.content = content;
        this.isFavorite = isFavorite;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
