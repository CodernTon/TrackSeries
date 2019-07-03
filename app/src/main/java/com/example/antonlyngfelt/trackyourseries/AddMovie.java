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
        dbHandler = createDbhandler();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbHandler.loadMovieName());
        movieName.setAdapter(adapter);
    }
    public void toastMessage(String toastMessage){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, toastMessage, Toast.LENGTH_LONG);
        toast.show();
    }
    public void goToPresentResult(View view) {
        Intent showResult = new Intent(AddMovie.this, PresentResults.class);
        startActivity(showResult);
    }
    public void removeText(){
        movieName.setText("");
    }
    public String getMovieName(){
        return movieName.getText().toString();
    }
    public DataBaseHelper createDbhandler(){
        dbHandler=new DataBaseHelper(this, null, null, 1);
        return dbHandler;
    }
    public void addMovie(View view) {
        if(movieName.getText().toString().equals("")|| movieName.getText().equals(null)) {
            toastMessage("Must name a movie");
        }
        else {
            try {
                dbHandler = createDbhandler();
                Movie movie = new Movie(0, getMovieName());
                if (dbHandler.addHandlerMovie(movie)) {
                    toastMessage("Movie added");
                    removeText();
                } else {
                    toastMessage("Movie already exist");
                }
            } catch (Exception e) {
                toastMessage("Something wrong when adding the movie");
            }
        }
        setUpAutofillMovies();
    }
    public void removeMovie(View view) {
        try {
            dbHandler = createDbhandler();
            boolean result = dbHandler.deleteHandlerMovie(getMovieName());
            if (result) {
                removeText();
                toastMessage("Movie removed");
            } else {
                toastMessage("No movie with that name");
            }
        }catch(Exception e){
            toastMessage("You did not remove the movie correct");
        }
        setUpAutofillMovies();
    }
}
