package com.example.antonlyngfelt.trackyourseries;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class AddSeries extends AppCompatActivity {
    AutoCompleteTextView serieName;
    EditText serieSeason;
    EditText serieEpisode;
    ArrayAdapter<String> adapter;
    DataBaseHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addseries);
    }
    @Override
    public void onStart(){
        super.onStart();
        setUpAutofillSeries();
    }
    @Override
    public void onStop(){
        super.onStop();
        serieName.setAdapter(null);
    }

    public static int add(int a, int b){
        return a+b;
    }
    public void resetText(){
        serieName.setText("");
        serieSeason.setText("");
        serieEpisode.setText("");
    }
    public void setUpAutofillSeries(){
        serieName = findViewById(R.id.serieName);
        dbHandler = new DataBaseHelper(this,null,null,1);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbHandler.loadSerieName());
        serieName.setAdapter(adapter);
        serieSeason = (EditText) findViewById(R.id.serieSeason);
        serieEpisode = (EditText) findViewById(R.id.serieEpisode);
    }
    public void toastMessage(String toastMessage){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
    public void goToShowSerie(View view) {
        Intent showSerie = new Intent(AddSeries.this, PresentResults.class);
        startActivity(showSerie);
    }

    public void addSerie(View view) {
        try {
            DataBaseHelper dbHandler = new DataBaseHelper(this, null, null, 1);
            String name = serieName.getText().toString();
            int season = Integer.parseInt(serieSeason.getText().toString());
            int episode = Integer.parseInt(serieEpisode.getText().toString());
            Serie serie = new Serie(0, name, season, episode);
            if (dbHandler.addHandlerSerie(serie)){
                toastMessage("Added");
                resetText();
            }
            else{
                toastMessage("Serie already exist");
            }
        }catch (Exception e){
            toastMessage("You did not add the series correct");

        }
        setUpAutofillSeries();
    }

    public void removeSerie(View view) {
        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this, null, null, 1);
            boolean result = dataBaseHelper.deleteHandlerSerie(serieName.getText().toString());
            if (result) {
                resetText();
                toastMessage("Serie removed");

            } else {
                toastMessage("No serie with that name");
            }
        }catch(Exception e){
            toastMessage("You did not remove the series correct");
        }
        setUpAutofillSeries();
    }

    public void updateSerie(View view) {
        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this, null, null, 1);
            boolean result = dataBaseHelper.updateHandlerSerie(
                    serieName.getText().toString(),
                    Integer.parseInt(serieSeason.getText().toString()),
                    Integer.parseInt(serieEpisode.getText().toString()));
            if (result) {
                resetText();
                toastMessage("Uppdated Serie");
            } else {
                toastMessage("No serie with that name");
            }
        }catch(Exception e){
            toastMessage("Check your input");
        }
        setUpAutofillSeries();
    }
}
