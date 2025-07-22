package com.martinrobles.dibujando;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DrawingActivity extends AppCompatActivity {

    private View canvas; // Se crea el lienzo
    private Paint paint; // Se crea el pincel
    private Bitmap bitmap; // Se crea el mapa de bits
    private CanvasView canvasView; // Se crea el lienzo para dibujar


    // Se crea la actividad de dibujo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Se llama al método de la superclase
        setContentView(R.layout.activity_drawing); // Se establece el contenido de la actividad
        paint = new Paint(); // Se crea el pincel
        canvasView = findViewById(R.id.canvasView); // Se crea el lienzo para dibujar
        canvas = findViewById(R.id.canvas); // Se crea el lienzo
        Button buttonHome = findViewById(R.id.button_home); // Se crea el botón para regresar a la pantalla principal
        Button buttonClear = findViewById(R.id.button_clear); // Se crea el botón para borrar el lienzo
        Button btnCirculoAzul = findViewById(R.id.btnCirculoAzul); // Se crea el botón para cambiar el color del pincel a azul
        Button btnCirculoRojo = findViewById(R.id.btnCirculoRojo); // Se crea el botón para cambiar el color del pincel a rojo
        Button btnCirculoVerde = findViewById(R.id.btnCirculoVerde); // Se crea el botón para cambiar el color del pincel a verde
        Button btnEstrella = findViewById(R.id.btnEstrella); // Se crea el botón para dibujar una imagen de una estrella
        Button btnImagenCara = findViewById(R.id.btnImagenCara); // Se crea el botón para dibujar una imagen de una cara

        // Regresa a la pantalla principal
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrawingActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        // Borra el lienzo
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.clearCanvas();
            }
        });

        // Cambia el color del pincel a azul
        btnCirculoAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.BLUE);
                canvasView.setPaintColor(Color.BLUE);

            }
        });

        // Cambia el color del pincel a rojo
        btnCirculoRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.RED);
                canvasView.setPaintColor(Color.RED);
            }
        });

        // Cambia el color del pincel a verde
        btnCirculoVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.GREEN);
                canvasView.setPaintColor(Color.GREEN);
            }
        });

        // Dibuja una imagen de una estrella
        btnEstrella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imagen_estrella);
                canvasView.setBrushBitmap(bitmap);
            }
        });

        // Dibuja una imagen de una cara
        btnImagenCara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imagen_cara);
                canvasView.setBrushBitmap(bitmap);
            }
        });
    }





}
