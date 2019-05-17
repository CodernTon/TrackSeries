package com.example.antonlyngfelt.trackyourseries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class AddSerie extends AppCompatActivity {
    Serie test = new Serie("Game of thrones",2, 3);
    private Button addSerie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addserie);

        addSerie = findViewById(R.id.button);
        addSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToMain();
            }
        });
    }
    public void goBackToMain(){
        Intent goBack = new Intent(AddSerie.this, MainActivity.class );
        startActivity(goBack);
    }
}
