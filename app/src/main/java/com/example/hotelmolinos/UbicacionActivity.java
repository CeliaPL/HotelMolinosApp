package com.example.hotelmolinos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class UbicacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);

        //Declaro dos variables que llevan las coordenadas de latitud y longitud cogidas de Google Maps
        double latitud = 37.173294290894624;
        double longitud = -3.5933825147781455;
        String nombreHotel = "Hotel Molinos";
        String direccion = "Calle Molinos 12, 18009 Granada";

        // Construir la URI de Google Maps
        String uri = "geo:" + latitud + "," + longitud + "?q=" + latitud + "," + longitud + "(" + nombreHotel + ")";

        // Crear un Intent con la acción ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        // Establecer el paquete de Google Maps
        mapIntent.setPackage("com.google.android.apps.maps");

        try {
            // Intentar abrir Google Maps
            startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            // Si no se puede abrir Google Maps, mostrar un mensaje de error
            Toast.makeText(this, "No se pudo abrir Google Maps", Toast.LENGTH_SHORT).show();
        }
    }
}