package com.martinrobles.dibujando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer; // Se crea el objeto MediaPlayer

    // Se crea la actividad principal
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.melodia_inicio); // Se crea el objeto MediaPlayer con la canción
        mediaPlayer.setLooping(true); // Se establece que la canción se repita
        mediaPlayer.start(); // Se inicia la canción

        Button button = findViewById(R.id.button); // Se crea el botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DrawingActivity.class);
                startActivity(intent);
            }
        });
    }

    // Se pausa la canción cuando la aplicación se pausa
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }



}