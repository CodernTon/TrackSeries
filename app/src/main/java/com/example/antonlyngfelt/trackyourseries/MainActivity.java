package com.example.antonlyngfelt.trackyourseries;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    List <Serie> serie;
    ListView serieList;
    TextView lst;
    TextView sName;
    TextView sSeason;
    TextView sEpisode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Design a homepage
        //Should have a list on the current episode you are at on the homepage
        startUp();

    }
    public void startUp(){
        serie = new ArrayList<>();
        serieList = (ListView)findViewById(R.id.listOfSerie);

        lst = (TextView) findViewById(R.id.lst);
        sName = (TextView) findViewById(R.id.seriename);
        sSeason = (TextView) findViewById(R.id.serieseason);
        sEpisode = (TextView) findViewById(R.id.serieepisode);

        Serie test1 = new Serie("Game of thrones",2, 3);
        Serie test2 = new Serie("The Office",6, 5);

        serie.add(test1);
        serie.add(test2);

        ArrayAdapter arrAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, serie);
        serieList.setAdapter(arrAdapter);
        // Start everything up
    }

    public void goToAddSerie(View view) {
        Intent addASerie = new Intent(MainActivity.this, AddSerie.class);
        startActivity(addASerie);
    }

    public void startUI(){


    }

    public void loadSeries(View view) {
        DataBaseHelper dbHandler = new DataBaseHelper(this, null, null, 1);
        lst.setText(dbHandler.loadHandler());
        sName.setText("");
        sSeason.setText("");
        sEpisode.setText("");
    }


    public void addSerie(View view) {
        DataBaseHelper dbHandler = new DataBaseHelper(this, null, null, 1);
        String name = sName.getText().toString();
        int season = parseInt(sSeason.getText().toString());
        int episode = parseInt(sEpisode.getText().toString());
        Serie serie = new Serie(name,season,episode);
        dbHandler.addHandler(serie);
        sName.setText("");
        sSeason.setText("");
        sEpisode.setText("");
    }
    public void findSerie(View view) {
        DataBaseHelper dbHandler = new DataBaseHelper(this, null, null, 1);
        String test="test";
        Serie serie =
                dbHandler.findHandler(test);//sName.getText().toString() bugg
        if (serie != null) {
            lst.setText(String.valueOf(serie.getName()) + " " + serie.getSeason() + " " + serie.getEpisode() + System.getProperty("line.separator"));
            sName.setText("");
            sSeason.setText("");
            sEpisode.setText("");
        } else {
            lst.setText("No Match Found");
            sName.setText("");
            sSeason.setText("");
            sEpisode.setText("");
        }
    }

    public void removeSerie(View view) {
        DataBaseHelper dbHandler = new DataBaseHelper(this, null,
                null, 1);
        boolean result = dbHandler.deleteHandler(
                sName.getText().toString());
        if (result) {
            sName.setText("");
            sSeason.setText("");
            sEpisode.setText("");
            lst.setText("Record Deleted");
        } else {
            lst.setText("No Match Found");
        }
    }

    public void updateSerie(View view) {
        DataBaseHelper dbHandler = new DataBaseHelper(this, null,
                null, 1);
        boolean result = dbHandler.updateHandler(
                sName.getText().toString(), Integer.parseInt(sSeason.getText().toString()),
                Integer.parseInt(sEpisode.getText().toString()));
        if (result) {
            sName.setText("");
            sSeason.setText("");
            sEpisode.setText("");
            lst.setText("Record Updated");
        } else
            sName.setText("No Match Found");
    }
}
