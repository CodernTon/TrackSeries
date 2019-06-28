package com.example.antonlyngfelt.trackyourseries;

public class Serie {

    private int serieID;
    private String name;
    private int season;
    private int episode;

    public Serie(){}

    public Serie(int serieID, String names, int seasons, int episodes){
        this.serieID = serieID;
        this.name = names;
        this.season = seasons;
        this.episode = episodes;
    }
    public void setSerieID(int serieID) {
        this.serieID = serieID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSeason(int season) {
        this.season = season;
    }
    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public int getSerieID() { return this.serieID; }
    public String getName() {
        return this.name;
    }
    public int getSeason() {
        return this.season;
    }
    public int getEpisode() {
        return this.episode;
    }


    @Override
    public String toString(){
        return name +" S"+ season +" E"+ episode;
    }
}
