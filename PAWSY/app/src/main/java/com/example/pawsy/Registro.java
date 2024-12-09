package com.example.pawsy;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre, etTelefono, etCorreo, etContrasena;
    Button botonRegistrar, botonRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        etNombre = findViewById(R.id.nombre);
        etTelefono = findViewById(R.id.telefono);
        etCorreo = findViewById(R.id.correo);
        etContrasena = findViewById(R.id.contrasena);

        botonRegistrar = findViewById(R.id.botonRegistrarse);
        botonRegresar = findViewById(R.id.botonRegresa);


        botonRegistrar.setOnClickListener(this);
        botonRegresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonRegistrarse) {
            registrarUsuario();
        } else if (v.getId() == R.id.botonRegresa) {
            finish();
        }
    }

    private void registrarUsuario() {

        String nombre = etNombre.getText().toString();
        String telefono = etTelefono.getText().toString();
        String correo = etCorreo.getText().toString();
        String contrasena = etContrasena.getText().toString();


        if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Base admin = new Base(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("telefono", telefono);
        registro.put("correo", correo);
        registro.put("contrasena", contrasena);

        long resultado = db.insert("mascotas", null, registro);

        if (resultado != -1) {
            Toast.makeText(this, "Fuiste registrado con éxito", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } else {
            Toast.makeText(this, "Algo paso al registrarte, por favor vuelve a intentarlo", Toast.LENGTH_SHORT).show();
        }


        db.close();
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etTelefono.setText("");
        etCorreo.setText("");
        etContrasena.setText("");
    }
}
