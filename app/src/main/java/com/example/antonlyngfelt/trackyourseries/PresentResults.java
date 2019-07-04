package com.example.antonlyngfelt.trackyourseries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class PresentResults extends AppCompatActivity {
    ListView serieList;
    ListView movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentresult);
    }
    @Override
    public void onStart(){
        super.onStart();
        showSeries();
        showMovies();
        addTitlesToList();
    }
    @Override
    public void onStop(){
        super.onStop();
    }
    public void addTitlesToList(){

    }

    public void goToAddSerie(View view){
        Intent goToAddSerie = new Intent(PresentResults.this, AddSeries.class );
        startActivity(goToAddSerie);
    }
    public void goToAddMovie(View view){
        Intent goToAddMovie = new Intent(PresentResults.this, AddMovie.class );
        startActivity(goToAddMovie);
    }
    public void showSeries(){
        DataBaseHelper dbhandler = new DataBaseHelper(this, null, null, 1);
        serieList = (ListView)findViewById(R.id.listOfSeries);
        List <Serie> listOfSeries = dbhandler.getAllSeriesInDb();
        ArrayAdapter<Serie> arrAdapter = new ArrayAdapter<Serie>(this, android.R.layout.simple_list_item_1, listOfSeries);
        serieList.setAdapter(arrAdapter);
    }
    public void showMovies(){
        DataBaseHelper dbhandler = new DataBaseHelper(this, null, null, 1);
        movieList = (ListView)findViewById(R.id.listOfMovies);
        List<Movie> listOfMovies = dbhandler.loadHandlerMovie();
        ArrayAdapter<Movie> arrayAdapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1,listOfMovies);
        movieList.setAdapter(arrayAdapter);
    }
}
