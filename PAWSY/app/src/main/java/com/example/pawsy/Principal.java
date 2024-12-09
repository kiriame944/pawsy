package com.example.pawsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class Principal extends AppCompatActivity implements View.OnClickListener {

    Button botonRegistrarMascota, botonVerMascotas;
    RadioButton radioRegistrarMascota, radioVerMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Inicializamos los botones y los radio buttons
        botonRegistrarMascota = findViewById(R.id.botonRegistrarMascota);
        botonVerMascotas = findViewById(R.id.botonVerMascotas);
        radioRegistrarMascota = findViewById(R.id.radioRegistrarMascota);
        radioVerMascotas = findViewById(R.id.radioVerMascotas);

        // Asignamos los eventos de click a los botones y radio buttons
        botonRegistrarMascota.setOnClickListener(this);
        botonVerMascotas.setOnClickListener(this);
        radioRegistrarMascota.setOnClickListener(this);
        radioVerMascotas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Comprobamos qué vista fue la que se tocó y actuamos en consecuencia
        if (v.getId() == R.id.botonRegistrarMascota || v.getId() == R.id.radioRegistrarMascota) {
            // Si se hace clic en el botón o el radio button de Registrar Mascota, redirigimos a la pantalla de registrar
            Intent intent = new Intent(Principal.this, RegistrarMascotas.class);
            startActivity(intent);
        } else if (v.getId() == R.id.botonVerMascotas || v.getId() == R.id.radioVerMascotas) {
            // Si se hace clic en el botón o el radio button de Ver Mascotas, redirigimos a la pantalla de ver mascotas
            Intent intent = new Intent(Principal.this, VerMascotas.class);
            startActivity(intent);
        }
    }
}
