package com.example.pawsy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Base extends SQLiteOpenHelper {

    public Base(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mascotas (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT, correo TEXT, contrasena TEXT)");

        db.execSQL("CREATE TABLE mascotas_perdidas ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "descripcion TEXT, " +
                "ubicacion TEXT, " +
                "fecha TEXT, " +
                "contacto TEXT, " +
                "id_usuario INTEGER, " +
                "foto TEXT, " +
                "FOREIGN KEY(id_usuario) REFERENCES mascotas(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("CREATE TABLE IF NOT EXISTS mascotas_perdidas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "descripcion TEXT, " +
                    "ubicacion TEXT, " +
                    "fecha TEXT, " +
                    "contacto TEXT, " +
                    "id_usuario INTEGER, " +
                    "foto TEXT, " +
                    "FOREIGN KEY(id_usuario) REFERENCES mascotas(id))");
        }
    }
}



