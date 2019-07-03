package com.example.antonlyngfelt.trackyourseries;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class AddMovie extends AppCompatActivity {
    AutoCompleteTextView movieName;
    DataBaseHelper dbHandler;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovies);
    }

    @Override
    public void onStart(){
        super.onStart();
        setUpAutofillMovies();
    }
    @Override
    public void onStop(){
        super.onStop();
        movieName.setAdapter(null);
    }
    public void setUpAutofillMovies(){
        movieName = findViewById(R.id.movieName);
        dbHandler = new DataBaseHelper(this,null,null,1);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbHandler.loadMovieName());
        movieName.setAdapter(adapter);
    }
    public void goToPresentResult(View view) {
        Intent showResult = new Intent(AddMovie.this, PresentResults.class);
        startActivity(showResult);
    }

    public void addMovie(View view) {
        Context context = getApplicationContext();
        Toast toast1 = Toast.makeText(context, "Movie added", Toast.LENGTH_LONG);
        Toast toast2 = Toast.makeText(context, "Movie already exist", Toast.LENGTH_LONG);
        Toast toast3 = Toast.makeText(context, "Something wrong when adding the movie", Toast.LENGTH_LONG);
        Toast toast4 = Toast.makeText(context, "Must name a movie",Toast.LENGTH_LONG);
        if(movieName.getText().toString().equals("")|| movieName.getText().equals(null)) {
            toast4.show();
        }
        else {
            try {
                DataBaseHelper dbHandler = new DataBaseHelper(this, null, null, 1);
                String name = movieName.getText().toString();
                Movie movie = new Movie(0, name);
                if (dbHandler.addHandlerMovie(movie)) {
                    toast1.show();
                    movieName.setText("");
                } else {
                    toast2.show();
                }
            } catch (Exception e) {
                toast3.show();
            }
        }
        setUpAutofillMovies();
    }
    public void removeMovie(View view) {
        Context context = getApplicationContext();
        Toast toast1 = Toast.makeText(context, "Movie removed", Toast.LENGTH_SHORT);
        Toast toast2 = Toast.makeText(context, "No movie with that name", Toast.LENGTH_SHORT);
        Toast toast3 = Toast.makeText(context, "You did not remove the movie correct", Toast.LENGTH_SHORT);
        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this, null, null, 1);
            boolean result = dataBaseHelper.deleteHandlerMovie(movieName.getText().toString());
            if (result) {
                movieName.setText("");
                toast1.show();
            } else {
                toast2.show();
            }
        }catch(Exception e){
            toast3.show();
        }
        setUpAutofillMovies();
    }
}
