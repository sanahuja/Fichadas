package com.fichadas.clasesdb;

/**
 * Created by informatica on 26/01/16.
 */
public class Tablas {

    // //-- -----------------------------------------------------
    // -- Nombres de las Tablas
    // //-- -----------------------------------------------------

    public static final String TABLA_FICHADAS_ORIGINAL   = "fichasdasoriginal";
    public static final String TABLA_FICHADAS_MODIFICADA = "fichasdasmodifiacada";
    public static final String TABLA_TIPO_HORA           = "tipodehora";

    public static final String BORRA_FICHADAS_ORIGINAL   = "DROP TABLE IF EXISTS '"+TABLA_FICHADAS_ORIGINAL+"';" ;
    public static final String BORRA_FICHADAS_MODIFICADA = "DROP TABLE IF EXISTS '"+TABLA_FICHADAS_MODIFICADA+"';" ;
    public static final String BORRA_TIPO_HORA           = "DROP TABLE IF EXISTS '"+TABLA_TIPO_HORA+"';" ;


    public static final String CREA_FICHADAS_ORIGINAL = " CREATE TABLE '"+TABLA_FICHADAS_ORIGINAL+"' (" +
            "'codigo'	 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "'entrada'	 INTEGER NOT NULL," +
            "'fechahora' DATETIME, " +
            "'extra'	 INTEGER NOT NULL );";

    public static final String CREA_FICHADAS_MODIFICASA = " CREATE TABLE '"+TABLA_FICHADAS_MODIFICADA+"' (" +
            "'codigo'	 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "'codoriginal'	 INTEGER NOT NULL," +
            "'entrada'	 INTEGER NOT NULL," +
            "'fechahora' DATETIME, " +
            "'extra'	 INTEGER NOT NULL );";

    public static final String CREA_TIPO_HORA = " CREATE TABLE '"+TABLA_TIPO_HORA+"' (" +
            "'codigo'	 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "'descripcion'	TEXT," +
            "'precio'    DOUBLE, " +
            "'extra'	 INTEGER NOT NULL );";


}
 /*--------------------------------- PARA EL MANEJADOR
 DROP TABLE FICHADAS;
DROP TABLE fichasdasmodifiacada;
DROP TABLE tipodehora;

CREATE TABLE 'fichasdasoriginal' (  'codigo'	 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
            'entrada'	 INTEGER NOT NULL,       'fechahora' DATETIME,      'extra'	 INTEGER NOT NULL );

    CREATE TABLE 'fichasdasmodifiacada' (
            'codigo'	 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
            'codoriginal'	 INTEGER NOT NULL,
            'entrada'	 INTEGER NOT NULL,
            'fechahora' DATETIME,
            'extra'	 INTEGER NOT NULL );

    CREATE TABLE 'tipodehora' (          'codigo'	 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
            'descripcion'	TEXT,       'precio'    DOUBLE,       'extra'	 INTEGER NOT NULL );
  */