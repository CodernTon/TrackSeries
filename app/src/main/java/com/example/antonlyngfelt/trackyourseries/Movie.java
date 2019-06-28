package com.example.antonlyngfelt.trackyourseries;

public class Movie {

    private int ID;
    private String title;
    private boolean seen;

    public Movie(){}

    public Movie(int ID, String title, boolean seen) {
        this.ID = ID;
        this.title = title;
        this.seen = seen;
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

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Override
    public String toString(){
        if(seen){
            return title+" Watched";
        }
        else{
            return  title+" Not watched";
        }
    }
}

