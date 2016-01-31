package com.fichadas.clasesdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by informatica on 12/01/16.
 */


public class DbLite extends SQLiteOpenHelper {

    private static final String DB_NAME = "DBfichadas";
    private static final int DB_VERSION = 1;




    public DbLite(Context contexto  ) {
        super(contexto, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla

        CreaTablas( db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL( Tablas.BORRA_FICHADAS_MODIFICADA);
        db.execSQL( Tablas.BORRA_FICHADAS_ORIGINAL);
        db.execSQL( Tablas.BORRA_TIPO_HORA);

        CreaTablas( db);


        //Se crea la nueva versión de la tabla
    }
    private void CreaTablas(SQLiteDatabase db)
    {
        db.execSQL(Tablas.TABLA_FICHADAS_ORIGINAL);
        db.execSQL(Tablas.TABLA_FICHADAS_MODIFICADA);
        db.execSQL(Tablas.TABLA_TIPO_HORA);


    }
}
