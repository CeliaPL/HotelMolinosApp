package com.example.hotelmolinos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.os.Handler;
import android.widget.ProgressBar;

public class InicioActivity extends AppCompatActivity {

    private static final int PROGRESS_MAX = 100;//tiempo maximo
    private static final int PROGRESS_DELAY = 50; // milliseconds
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        progressBar = findViewById(R.id.progressBar);//definicion de la barra de progreso

        // Carga de la barra de  progreso
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int progress = progressBar.getProgress() + 1;
                progressBar.setProgress(progress);
                if (progress < PROGRESS_MAX) {
                    handler.postDelayed(this, PROGRESS_DELAY);
                } else {
                    // Inicio de otra activity
                    startActivity(new Intent(InicioActivity.this, MainActivity.class));
                    finish(); // Finish MainActivity
                }
            }
        }, PROGRESS_DELAY);
    }
}
