package com.example.luisgarcia.lab09lite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MetodosSQL extends SQLiteOpenHelper {

    final String Crear_tabla="create table usuario(nombre text primary key, apellido text)";

    public MetodosSQL(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, nombre, factory, version);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        //aquí creamos la tabla de usuario (nombre, apellido)
        db.execSQL(Crear_tabla);

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {

        db.execSQL("drop table if exists usuario");

        db.execSQL(Crear_tabla);

    }

}
