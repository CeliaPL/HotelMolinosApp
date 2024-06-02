package com.example.hotelmolinos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //declaración del botón Entrar
    Button btEntrar;
    Button btSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recuper el botón de la interfaz
        btEntrar=findViewById(R.id.btEntrar);
        btSalir = findViewById(R.id.btSalir);



        //implementar el listener del botón Entrar
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creamos la actividad para que al pulsar el botón, cambie a la activity de Menú
                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
            }
        });

        // Implementar el listener del botón "Salir"
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar la aplicación
                finish();
            }
        });

    }
}