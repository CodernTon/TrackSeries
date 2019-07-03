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
    public void setUpAutofillSeries(){
        serieName = findViewById(R.id.serieName);
        dbHandler = new DataBaseHelper(this,null,null,1);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbHandler.loadSerieName());
        serieName.setAdapter(adapter);
        serieSeason = (EditText) findViewById(R.id.serieSeason);
        serieEpisode = (EditText) findViewById(R.id.serieEpisode);
    }
    public void goToShowSerie(View view) {
        Intent showSerie = new Intent(AddSeries.this, PresentResults.class);
        startActivity(showSerie);
    }
    public void addSerie(View view) {
        Context context = getApplicationContext();
        Toast toast1 = Toast.makeText(context, "Serie added", Toast.LENGTH_SHORT);
        Toast toast2 = Toast.makeText(context, "Serie already exist", Toast.LENGTH_SHORT);
        Toast toast3 = Toast.makeText(context, "You did not add the series correct", Toast.LENGTH_SHORT);
        try {
            DataBaseHelper dbHandler = new DataBaseHelper(this, null, null, 1);
            String name = serieName.getText().toString();
            int season = Integer.parseInt(serieSeason.getText().toString());
            int episode = Integer.parseInt(serieEpisode.getText().toString());
            Serie serie = new Serie(0, name, season, episode);
            if (dbHandler.addHandlerSerie(serie)){
                toast1.show();
                serieName.setText("");
                serieSeason.setText("");
                serieEpisode.setText("");
            }
            else{
                toast2.show();
            }
        }catch (Exception e){
            toast3.show();
        }
        setUpAutofillSeries();
    }

    public void removeSerie(View view) {
        Context context = getApplicationContext();
        Toast toast1 = Toast.makeText(context, "Serie removed", Toast.LENGTH_SHORT);
        Toast toast2 = Toast.makeText(context, "No serie with that name", Toast.LENGTH_SHORT);
        Toast toast3 = Toast.makeText(context, "You did not remove the series correct", Toast.LENGTH_SHORT);
        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this, null, null, 1);
            boolean result = dataBaseHelper.deleteHandlerSerie(serieName.getText().toString());
            if (result) {
                serieName.setText("");
                serieSeason.setText("");
                serieEpisode.setText("");
                toast1.show();
            } else {
                toast2.show();
            }
        }catch(Exception e){
            toast3.show();
        }
        setUpAutofillSeries();
    }

    public void updateSerie(View view) {
        Context context = getApplicationContext();
        Toast toast1 = Toast.makeText(context, "Uppdated Serie", Toast.LENGTH_SHORT);
        Toast toast2 = Toast.makeText(context, "No serie with that name", Toast.LENGTH_SHORT);
        Toast toast3=Toast.makeText(context,"Check your input",Toast.LENGTH_SHORT);
        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this, null, null, 1);
            boolean result = dataBaseHelper.updateHandlerSerie(
                    serieName.getText().toString(),
                    Integer.parseInt(serieSeason.getText().toString()),
                    Integer.parseInt(serieEpisode.getText().toString()));
            if (result) {
                serieName.setText("");
                serieSeason.setText("");
                serieEpisode.setText("");
                toast1.show();
            } else {
                toast2.show();
            }
        }catch(Exception e){
            toast3.show();
        }
        setUpAutofillSeries();
    }
}
