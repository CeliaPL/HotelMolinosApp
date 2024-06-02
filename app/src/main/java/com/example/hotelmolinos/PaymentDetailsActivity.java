package com.example.hotelmolinos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentDetailsActivity extends AppCompatActivity {

    TextView txtId, txtAmount, txtStatus, txtFechas;
    SharedPreferences sharedPreferences;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        txtId = findViewById(R.id.txtId);
        txtAmount = findViewById(R.id.txtAmount);
        txtStatus = findViewById(R.id.txtStatus);
        txtFechas = findViewById(R.id.txtFechas);
        btnPay = findViewById(R.id.btnPay);

        sharedPreferences = getSharedPreferences("Reserva", Context.MODE_PRIVATE);
        String fechaInicio = getIntent().getStringExtra("fecha_inicio");
        String fechaFin = getIntent().getStringExtra("fecha_fin");

        Log.d("PaymentDetailsActivity", "Fecha de entrada obtenida: " + fechaInicio);
        Log.d("PaymentDetailsActivity", "Fecha de salida obtenida: " + fechaFin);

        double precio = getIntent().getDoubleExtra("precio", 0.00);
        String textoFechas = "Confirmación de la reserva : \n" + "\nFecha de entrada: " + fechaInicio + "\nFecha de salida: " + fechaFin + "\nPrecio: €" + precio + "\n";
        txtFechas.setText(textoFechas);
    }

    private void showDetails(String paymentAmount) {
        txtId.setText(sharedPreferences.getString("id", ""));
        txtStatus.setText(sharedPreferences.getString("status", ""));
        txtAmount.setText(String.format("Confirmación de la reserva", paymentAmount));
    }

    public void onPagarAhoraClick(View view) {
        Intent intent = new Intent(this, PayPalActivity.class);
        startActivity(intent);
    }

    public void onAtrasClick(View view) {
        Intent intent = new Intent(PaymentDetailsActivity.this, HabitacionesActivity.class);
        startActivity(intent);
    }
}
