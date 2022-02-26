package edu.udb.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class ClientSQLiteOpenHelper extends SQLiteOpenHelper{

    public ClientSQLiteOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table articulos(codigo int primary key,descripcion text,precio real)");
        db.execSQL("create table personas(codigo int primary key, nombres text, edad int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE articulos");
        db.execSQL("DROP TABLE personas");
        onCreate(db);
    }
}
