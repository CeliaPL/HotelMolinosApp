package com.example.hotelmolinos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class PayPalActivity extends AppCompatActivity {

    private static final String TAG = "PayPalActivity";
    private static final String PAYPAL_CLIENT_ID = "ASbL4xzJeg06e27cfhS9gLRklZ9Np2-8afq-HOIPWi8Fn6okbtGB7-vCV2jI6nQoqzHJxkgc1nV_OBtG"; // Reemplaza esto con tu client ID
    private static final int PAYPAL_REQUEST_CODE = 123;

    private static final PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal);

        // Iniciar el servicio de PayPal
        Log.d(TAG, "Iniciando servicio de PayPal");
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        Button btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Botón de pago presionado");
                processPayment();



            }
        });
    }

    private void processPayment() {
        double amount = getIntent().getDoubleExtra("precio", 0.00);
        Log.d(TAG, "Cantidad a pagar: " + amount);
        PayPalPayment payment = new PayPalPayment(new BigDecimal(amount), "EUR",
                "Descripción del pago", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        Log.d(TAG, "Iniciando PaymentActivity");
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                    if (confirmation != null) {
                        try {
                            String paymentDetails = confirmation.toJSONObject().toString(4);
                            Log.i(TAG, paymentDetails);
                            // Manejar los detalles del pago aquí o mostrarlos en una nueva actividad
                        } catch (JSONException e) {
                            Log.e(TAG, "Error: ", e);
                        }
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.i(TAG, "User canceled the payment.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(TAG, "Invalid payment configuration.");
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Deteniendo servicio de PayPal");
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}
