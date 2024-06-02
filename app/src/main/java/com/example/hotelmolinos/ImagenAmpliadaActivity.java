package com.example.hotelmolinos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.hotelmolinos.adaptador.GaleriaFotosAdaptador;

public class ImagenAmpliadaActivity extends AppCompatActivity {
    //declaramos el ImageView y el adaptador
    ImageView imageView;
    GaleriaFotosAdaptador galeriaFotosAdaptador;

    // Array de imágenes
    private int[] imagenes = {
            R.drawable.vistasterraza,
            R.drawable.vistaspalace,
            R.drawable.vistasalhambra,
            R.drawable.recepcion,
            R.drawable.desayuno,
            R.drawable.hotelmolinosportada,
            R.drawable.fachadaprincipal,
            R.drawable.habitaciontwin,
            R.drawable.banotwin,
            R.drawable.doble,
            R.drawable.habitacionmatrimonio,
            R.drawable.habitaciontriple,
            R.drawable.cuadruple,
            R.drawable.banera,
            R.drawable.parking
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_ampliada);

        // Recuperar la posición de la imagen seleccionada del intent
        int posicionSeleccionada = getIntent().getIntExtra("misImagenes", 0); // 0 es la posición predeterminada

        // Obtener la referencia del ImageView en el layout
        ImageView imageView = findViewById(R.id.imageView);

        // Establecer la imagen ampliada en el ImageView
        imageView.setImageResource(imagenes[posicionSeleccionada]);
    }



}
