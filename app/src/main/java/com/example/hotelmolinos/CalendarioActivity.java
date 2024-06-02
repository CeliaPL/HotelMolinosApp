package com.example.hotelmolinos;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarioActivity extends AppCompatActivity {

    private TextView tvFechaEntrada, tvFechaSalida;
    private Button btFechaEntrada, btFechaSalida, btGuardarFechas, btnVolverHabitaciones;
    private Calendar fechaEntrada, fechaSalida;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        tvFechaEntrada = findViewById(R.id.tvFechaEntrada);
        tvFechaSalida = findViewById(R.id.tvFechaSalida);
        btFechaEntrada = findViewById(R.id.btFechaEntrada);
        btFechaSalida = findViewById(R.id.btFechaSalida);
        btGuardarFechas = findViewById(R.id.btGuardarFechas);
        btnVolverHabitaciones = findViewById(R.id.btnVolverAHabitaciones);

        sharedPreferences = getSharedPreferences("Reserva", Context.MODE_PRIVATE);

        btFechaEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(true);
            }
        });

        btFechaSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(false);
            }
        });

        btGuardarFechas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarFechasSeleccionadas();
            }
        });

        btnVolverHabitaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAHabitaciones();
            }
        });
    }

    private void showDatePickerDialog(final boolean isEntrada) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                if (isEntrada) {
                    fechaEntrada = selectedDate;
                    tvFechaEntrada.setText(dateFormat.format(selectedDate.getTime()));
                } else {
                    fechaSalida = selectedDate;
                    tvFechaSalida.setText(dateFormat.format(selectedDate.getTime()));
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void guardarFechasSeleccionadas() {
        if (fechaEntrada != null && fechaSalida != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String fechaInicio = dateFormat.format(fechaEntrada.getTime());
            String fechaFin = dateFormat.format(fechaSalida.getTime());

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fecha_inicio", fechaInicio);
            editor.putString("fecha_fin", fechaFin);
            editor.apply();

            Toast.makeText(this, "Fechas guardadas correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Por favor, selecciona las fechas de entrada y salida", Toast.LENGTH_SHORT).show();
        }
    }

    public void volverAHabitaciones() {
        Intent intent = new Intent(this, HabitacionesActivity.class);
        startActivity(intent);
    }
}
