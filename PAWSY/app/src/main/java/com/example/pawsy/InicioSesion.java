package com.example.pawsy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InicioSesion extends AppCompatActivity implements View.OnClickListener {

        EditText etCorreo, etContrasena;
    Button botonIniciarSesion, botonRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        etCorreo = findViewById(R.id.correoInicioSesion);
        etContrasena = findViewById(R.id.contrasenaInicioSesion);

        botonIniciarSesion = findViewById(R.id.botonIniciarSesion);
        botonRegresar = findViewById(R.id.botonRegresarInicio);


        botonIniciarSesion.setOnClickListener(this);
        botonRegresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonIniciarSesion) {
            validarCredenciales();
        } else if (v.getId() == R.id.botonRegresarInicio) {
            finish();
        }
    }

    private void validarCredenciales() {
        String correo = etCorreo.getText().toString();
        String contrasena = etContrasena.getText().toString();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Base admin = new Base(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM mascotas WHERE correo=? AND contrasena=?",
                new String[]{correo, contrasena});

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String nombreUsuario = cursor.getString(cursor.getColumnIndex("nombre"));
            Toast.makeText(this, "¡Bienvenido, " + nombreUsuario + "!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(InicioSesion.this, Principal.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }
}
