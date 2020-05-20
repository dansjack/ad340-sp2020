package com.example.djackad340;

import android.graphics.drawable.Drawable;

public class MatchItemModel {
    private String name;
    private String description;
    private Drawable image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) { this.image = image; }

    public MatchItemModel(String name, String description, Drawable image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
