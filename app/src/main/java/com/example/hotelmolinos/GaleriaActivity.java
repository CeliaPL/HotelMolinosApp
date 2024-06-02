package com.example.hotelmolinos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.example.hotelmolinos.adaptador.GaleriaFotosAdaptador;

public class GaleriaActivity extends AppCompatActivity {

    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);


        //declaramos el gridView y lo inicializamos
        gridView = (GridView) findViewById(R.id.vg_galeria);

        //llamamos a la clase que contiene el adaptador
        gridView.setAdapter(new GaleriaFotosAdaptador(this));

        //le decimos lo que hará cuando el usuario toque la imagen
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //aquí mandamos la imagen seleccionada a otro activity para que se vea más ampliada

                Intent intent = new Intent(getApplicationContext(), ImagenAmpliadaActivity.class);

                //le decimos que se lleve la imagen seleccionada al otro activity
                intent.putExtra("misImagenes", position);//creamos una llave para el envio, que se llamará mis Imagenes

                Log.d("GaleriaActivity", "Posición seleccionada: " + position); // Verificar la posición seleccionada
                //iniciamos la actividad
                startActivity(intent);
            }
        });


    }
}

