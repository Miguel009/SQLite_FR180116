package edu.udb.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private EditText et1,et2,et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et1=(EditText)findViewById(R.id.et6);
        et2=(EditText)findViewById(R.id.et7);
        et3=(EditText)findViewById(R.id.et8);
    }
    public void alta(View v) {
        ClientSQLiteOpenHelper admin = new ClientSQLiteOpenHelper(this,"administracion", null, 2);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String cod = et1.getText().toString();
        String descri = et2.getText().toString();
        String pre = et3.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("codigo", cod);
        registro.put("nombres", descri);
        registro.put("edad", pre);

        try {
            bd.insertOrThrow("personas", null, registro);
            bd.close();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            Toast.makeText(this, "Se cargaron los datos del Persona",Toast.LENGTH_SHORT).show();
        } catch (SQLiteException e) {
            Toast.makeText(this, "ERROR!! No se cargaron los datos del Persona" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    public void consultaporcodigo(View v) {
        ClientSQLiteOpenHelper admin = new ClientSQLiteOpenHelper(this,"administracion", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        Cursor fila = bd.rawQuery("select nombres,edad from personas where codigo=" + cod, null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
        } else
            Toast.makeText(this, "No existe una persona con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void consultapordescripcion(View v) {
        ClientSQLiteOpenHelper admin = new ClientSQLiteOpenHelper(this,"administracion", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String descri = et2.getText().toString();
        Cursor fila = bd.rawQuery("select codigo,edad from personas where nombres='" + descri +"'", null);
        if (fila.moveToFirst()) {
            et1.setText(fila.getString(0));
            et3.setText(fila.getString(1));
        } else
            Toast.makeText(this, "No existe un artículo con dicha descripción",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v) {
        ClientSQLiteOpenHelper admin = new ClientSQLiteOpenHelper(this,"administracion", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod= et1.getText().toString();
        int cant = bd.delete("personas", "codigo=" + cod, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró el artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        ClientSQLiteOpenHelper admin = new ClientSQLiteOpenHelper(this,"administracion", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descri = et2.getText().toString();
        String pre = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        int cant = bd.update("personas", registro, "codigo=" + cod, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe un artículo con el código ingresado",
                    Toast.LENGTH_SHORT).show();
    }
}