package com.example.hotelmolinos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    //creamos nuestros botones de Menu
    Button btHabitaciones;
    Button btUbicacion;
    Button btReservas;
    Button btGaleria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //recuperamos los botones de la interfaz
        btHabitaciones = findViewById(R.id.btHabitaciones);
        btUbicacion = findViewById(R.id.btUbicacion);
        btReservas = findViewById(R.id.btReservas);
        btGaleria = findViewById(R.id.btGaleria);

        //implementamos los botones con el evento onClick
        btHabitaciones.setOnClickListener(this);
        btUbicacion.setOnClickListener(this);
        btReservas.setOnClickListener(this);
        btGaleria.setOnClickListener(this);


    }

    //a través del método, cuando pulsamos cada uno de los botones, nos dirigimos a su activity correspondiente
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btHabitaciones) {
            startActivity(new Intent(getApplicationContext(), HabitacionesActivity.class));
        } else if (v.getId() == R.id.btUbicacion) {
            // Google maps
            startActivity(new Intent(getApplicationContext(), UbicacionActivity.class));

        } else if (v.getId() == R.id.btReservas) {
            // WhatsApp
            reservas();
            startActivity(new Intent(getApplicationContext(), ReservasActivity.class));
        } else if (v.getId() == R.id.btGaleria) {
            startActivity(new Intent(getApplicationContext(), GaleriaActivity.class));
        }
    }

    public void reservas(){

    }
}