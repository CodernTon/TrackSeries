package com.example.antonlyngfelt.trackyourseries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AddMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovies);
    }
    public void goToPresentResult(View view) {
        Intent showResult = new Intent(AddMovie.this, PresentResults.class);
        startActivity(showResult);
    }
}
