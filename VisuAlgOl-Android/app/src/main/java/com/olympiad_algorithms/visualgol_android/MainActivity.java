package com.olympiad_algorithms.visualgol_android;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button toAlgorithms;
    GifImageView giff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("");

        toAlgorithms = findViewById(R.id.toAlgorithms);
        toAlgorithms.setOnClickListener(this);

        giff = findViewById(R.id.SimulatedAnnealing);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toAlgorithms) {
            Intent intent = new Intent(this, Algorithms.class);
            startActivity(intent);
        }
    }
}