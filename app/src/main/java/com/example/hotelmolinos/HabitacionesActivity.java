package com.example.hotelmolinos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HabitacionesActivity extends AppCompatActivity implements View.OnClickListener {

    Button btIndividual, btDoble, btTriple, btCuadruple;
    Button btDisponibilidadIndividual, btDisponibilidadDoble, btDisponibilidadTriple, btDisponibilidadCuadruple;

    private static final double PRECIO_INDIVIDUAL = 40.00;
    private static final double PRECIO_DOBLE = 75.50;
    private static final double PRECIO_TRIPLE = 110.00;
    private static final double PRECIO_CUADRUPLE = 175.50;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitaciones);

        btIndividual = findViewById(R.id.btIndividual);
        btDoble = findViewById(R.id.btDoble);
        btTriple = findViewById(R.id.btTriple);
        btCuadruple = findViewById(R.id.btCuadruple);

        btIndividual.setOnClickListener(this);
        btDoble.setOnClickListener(this);
        btTriple.setOnClickListener(this);
        btCuadruple.setOnClickListener(this);

        btDisponibilidadIndividual = findViewById(R.id.btDisponibilidadIndividual);
        btDisponibilidadDoble = findViewById(R.id.btDisponibilidadDoble);
        btDisponibilidadTriple = findViewById(R.id.btDisponibilidadTriple);
        btDisponibilidadCuadruple = findViewById(R.id.btDisponibilidadCuadruple);

        btDisponibilidadIndividual.setOnClickListener(this);
        btDisponibilidadDoble.setOnClickListener(this);
        btDisponibilidadTriple.setOnClickListener(this);
        btDisponibilidadCuadruple.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("Reserva", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btIndividual) {
            realizarPago(PRECIO_INDIVIDUAL);
        } else if (v.getId() == R.id.btDoble) {
            realizarPago(PRECIO_DOBLE);
        } else if (v.getId() == R.id.btTriple) {
            realizarPago(PRECIO_TRIPLE);
        } else if (v.getId() == R.id.btCuadruple) {
            realizarPago(PRECIO_CUADRUPLE);
        } else if (v.getId() == R.id.btDisponibilidadIndividual || v.getId() == R.id.btDisponibilidadDoble || v.getId() == R.id.btDisponibilidadTriple || v.getId() == R.id.btDisponibilidadCuadruple) {
            abrirCalendario();
        }
    }

    private void realizarPago(double precio) {
        String fechaInicio = sharedPreferences.getString("fecha_inicio", "");
        String fechaFin = sharedPreferences.getString("fecha_fin", "");
        if (fechaInicio.isEmpty() || fechaFin.isEmpty()) {
            Toast.makeText(this, "Por favor, selecciona las fechas primero.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, PaymentDetailsActivity.class);
        intent.putExtra("precio", precio);
        intent.putExtra("fecha_inicio", fechaInicio);
        intent.putExtra("fecha_fin", fechaFin);
        startActivity(intent);

        Log.d("HabitacionesActivity", "Llamada a PaymentDetailsActivity con fechas: " + fechaInicio + " - " + fechaFin);
    }

    private void abrirCalendario() {
        Intent intent = new Intent(this, CalendarioActivity.class);
        startActivity(intent);
    }
}
