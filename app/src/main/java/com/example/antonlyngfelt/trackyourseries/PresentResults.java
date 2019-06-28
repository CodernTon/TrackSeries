package com.example.antonlyngfelt.trackyourseries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class PresentResults extends AppCompatActivity {
    ListView serieList;
    ListView movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentresult);
        showSeries();
        showMovies();
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
        List <Serie> listOfSeries = dbhandler.loadHandler();
        ArrayAdapter arrAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfSeries);
        serieList.setAdapter(arrAdapter);
    }
    public void showMovies(){
        movieList = (ListView)findViewById(R.id.listOfMovies);
        List<Movie> listOfMovies = new LinkedList<>();
        listOfMovies.add(new Movie(0,"Avengers",false));
        listOfMovies.add(new Movie(1,"Avengers Infinity War",true));
        listOfMovies.add(new Movie(2,"Avengers Endgame",true));
        listOfMovies.add(new Movie(3,"Jurrasic Park 1",true));
        listOfMovies.add(new Movie(4,"Jurassic Park 2",true));
        listOfMovies.add(new Movie(5,"Jurassic Park 3",false));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,listOfMovies);
        movieList.setAdapter(arrayAdapter);
    }
}
