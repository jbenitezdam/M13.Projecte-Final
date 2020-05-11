package com.example.myownbusiness;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    final String CREATE_DATABASE = "CREATE TABLE usuario(codigo_usuario VARCHAR,nombre_cuenta VARCHAR,contraseña_cuenta VARCHAR,correo_elec VARCHAR,fecha_naci VARCHAR)";
    final String DROP_DATABASE = "DROP TABLE IF EXISTS usuario";
    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_DATABASE);
        onCreate(db);
    }

    public void delete(SQLiteDatabase db){
         db.execSQL("DELETE FROM usuarios");
    }
}
