package com.example.hotelmolinos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReservasActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_WHATSAPP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

// Llama al método reservas() cuando se inicie la actividad
        reservas();

    }

    @Override
    public void onClick(View v) {
        reservas();
    }
    private void reservas() {
        try {

            String numeroTelefono = "+34609392966"; // Número de teléfono del Hotel Molinos Granada
            String mensaje = "Hola, estoy interesado en obtener más información sobre la reserva.";
            String url = "https://wa.me/" + numeroTelefono+ "?text=" + Uri.encode(mensaje);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            // Aquí puedes agregar cualquier manejo de errores adicional, como mostrar un mensaje al usuario
            Toast.makeText(this, "No se pudo abrir el chat de WhatsApp", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_WHATSAPP) {
            // Aquí puedes manejar cualquier resultado que desees después de que la actividad de WhatsApp se haya cerrado
            // Por ejemplo, puedes mostrar un mensaje al usuario o realizar otras acciones.
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Chat de WhatsApp abierto correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se pudo abrir el chat de WhatsApp", Toast.LENGTH_SHORT).show();
            }
        }
    }
}