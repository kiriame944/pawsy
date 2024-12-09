package com.example.pawsy;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarMascotas extends AppCompatActivity {

    EditText etNombre, etDescripcion, etUbicacion, etFecha, etContacto;
    Button botonSubirDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascotas);

        etNombre = findViewById(R.id.nombreMascota);
        etDescripcion = findViewById(R.id.descripcionMascota);
        etUbicacion = findViewById(R.id.ubicacionMascota);
        etFecha = findViewById(R.id.fechaMascota);
        etContacto = findViewById(R.id.contactoMascota);
        botonSubirDatos = findViewById(R.id.botonSubirDatos);

        botonSubirDatos.setOnClickListener(v -> subirDatos());
    }

    @SuppressLint("Range")
    private int obtenerIdUsuario() {
        int idUsuario = -1;
        Base dbHelper = new Base(this, "administracion", null, 2);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Consulta para obtener el id del usuario logueado
        String correoDelUsuario = "correo@ejemplo.com";  // Aquí obtienes el correo del usuario logueado
        Cursor cursor = db.rawQuery("SELECT id FROM mascotas WHERE correo=?", new String[]{correoDelUsuario});
        if (cursor.moveToFirst()) {
            idUsuario = cursor.getInt(cursor.getColumnIndex("id"));
        }
        cursor.close();
        db.close();

        return idUsuario;
    }

    private void subirDatos() {
        String nombre = etNombre.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String ubicacion = etUbicacion.getText().toString();
        String fecha = etFecha.getText().toString();
        String contacto = etContacto.getText().toString();


        if (nombre.isEmpty() || descripcion.isEmpty() || ubicacion.isEmpty() || fecha.isEmpty() || contacto.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }


        int idUsuario = obtenerIdUsuario();

        if (idUsuario == -1) {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            return;
        }


        Base admin = new Base(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();


        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("descripcion", descripcion);
        valores.put("ubicacion", ubicacion);
        valores.put("fecha", fecha);
        valores.put("contacto", contacto);
        valores.put("id_usuario", idUsuario);

        long resultado = db.insert("mascotas_perdidas", null, valores);

        if (resultado != -1) {
            Toast.makeText(this, "Mascota registrada correctamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } else {
            Toast.makeText(this, "Error al registrar la mascota", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etDescripcion.setText("");
        etUbicacion.setText("");
        etFecha.setText("");
        etContacto.setText("");
    }
}

