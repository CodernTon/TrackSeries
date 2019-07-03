package com.example.antonlyngfelt.trackyourseries;

public class Movie {

    private int ID;
    private String title;

    public Movie(){}

    public Movie(int ID, String title) {
        this.ID = ID;
        this.title = title;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        return title;
    }
}

