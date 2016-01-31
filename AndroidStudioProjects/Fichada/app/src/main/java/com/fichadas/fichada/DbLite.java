package com.fichadas.fichada;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by informatica on 12/01/16.
 */


public class DbLite extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    //"CREATE TABLE fichadas (codigo INTEGER, nombre TEXT)";
    String sqlCreate = " CREATE TABLE 'fichadas' (" +
            "'codigo'	 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "'entrada'	 INTEGER NOT NULL," +
            "'fechahora' DATETIME, " +
            "'extra'	 INTEGER NOT NULL )";


    public DbLite(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS fichadas");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}
