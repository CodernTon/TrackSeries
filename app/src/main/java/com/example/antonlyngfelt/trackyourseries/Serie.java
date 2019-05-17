package com.example.antonlyngfelt.trackyourseries;

public class Serie {

    private String name;
    private int season;
    private int episode;

    public Serie(){

    }

    public Serie(String names, int seasons, int episodes){
        this.name = names;
        this.season = seasons;
        this.episode = episodes;
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

    public String getName() {
        return name;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }

    @Override
    public String toString(){
        return name +" Season: "+ season +" Episode: "+ episode;
    }
}
