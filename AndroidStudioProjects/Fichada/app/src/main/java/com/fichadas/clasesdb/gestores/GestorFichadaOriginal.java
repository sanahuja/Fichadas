package com.fichadas.clasesdb.gestores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fichadas.clasesdb.DbLite;
import com.fichadas.clasesdb.FichadaOriginal;
import com.fichadas.clasesdb.Tablas;
import com.fichadas.clasesdb.basecolums.bcFichadaOriginal;
import com.fichadas.fichada.R;
import com.fichadas.utils.UtilsFechaHora;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by informatica on 26/01/16.
 */
public class GestorFichadaOriginal  {
    private Context context;
    private SQLiteDatabase db;
    private DbLite openHelper; //Gestor de base de datos

    public GestorFichadaOriginal (Context contexto) {
        this.context = contexto;
        this.openHelper = new DbLite(this.context);
    }

    public GestorFichadaOriginal  open() {
        this.db = openHelper.getWritableDatabase(); //Crea/abre la base de datos para la lectura/escritura
        return this;
    }

    public void close() {
        this.db.close();
    }

    public void delete()
    {
        db.delete(Tablas.TABLA_FICHADAS_ORIGINAL, null, null);

    }

    public void deleteId(int idmuni )
    {
        String xwhere = bcFichadaOriginal.ID_CODIGO+"="+String.valueOf(idmuni);
        db.delete(Tablas.TABLA_FICHADAS_ORIGINAL, xwhere , null);
    }
    public void deleteFechaHora( String fechor )
    {
        String xwhere = bcFichadaOriginal.FECHAHORA+"='"+fechor+"'";
        db.delete(Tablas.TABLA_FICHADAS_ORIGINAL, xwhere , null);
    }
    public void deleteFecha( String fechor )
    {
        String fecha ="";

        if(fechor.length() > 10)
        {
             fecha = fechor.substring(0,10);
        }

        String Fini= fecha+" 00:00:00";
        String Ffin= fecha+" 23:59:59";

        String xwhere = bcFichadaOriginal.FECHAHORA+">='"+Fini+"' AND ";
         xwhere += bcFichadaOriginal.FECHAHORA+"<='"+Ffin+"'";

        db.delete(Tablas.TABLA_FICHADAS_ORIGINAL, xwhere , null);
    }
    public void deleteFechaAFecha( String fechor1, String fechor2 )
    {
        String fecha1 ="";
        String fecha2 ="";

        if(fechor1.length() > 10)
        {
            fecha1 = fechor1.substring(0,10);
        }
        if(fechor2.length() > 10)
        {
            fecha2 = fechor2.substring(0,10);
        }

        String Fini= fecha1+" 00:00:00";
        String Ffin= fecha2+" 23:59:59";

        String xwhere = bcFichadaOriginal.FECHAHORA+">='"+Fini+"' AND "+ bcFichadaOriginal.FECHAHORA+"<='"+Ffin+"'";

        db.delete(Tablas.TABLA_FICHADAS_ORIGINAL, xwhere , null);
    }
    public void deleteFhAFh( String fechor1, String fechor2 )
    {
      String xwhere = bcFichadaOriginal.FECHAHORA+">='"+fechor1+"' AND " +bcFichadaOriginal.FECHAHORA+"<='"+fechor2+"'";
      db.delete(Tablas.TABLA_FICHADAS_ORIGINAL, xwhere , null);
    }

    /*
     *    Seleccionamos por codigo el objeto a devolver
     */
    public FichadaOriginal selectId(int id) {
        FichadaOriginal fichadaOriginal = null;
        Cursor cursor = db.query(Tablas.TABLA_FICHADAS_ORIGINAL ,
                null, bcFichadaOriginal.ID_CODIGO+"=?", new String[] {Integer.toString(id)},
                null, null, null);

        if (cursor.moveToFirst())
        {

            fichadaOriginal = new FichadaOriginal();

            fichadaOriginal.setCodigo(cursor.getInt(0));
            fichadaOriginal.setEntSal(cursor.getInt(1));
            fichadaOriginal.setFechaHora(cursor.getString(2));
            fichadaOriginal.setExtra(cursor.getInt(3));
            if (cursor.getInt(1) == 0) {
                fichadaOriginal.setIdDrawable(R.drawable.boton_rojo1);
            }
            else
            {
                fichadaOriginal.setIdDrawable(R.drawable.boton_verde1);
            }
        }
        if (cursor != null && !cursor.isClosed()) {//Se cierra el cursor si no estÃ¡ cerrado ya
            cursor.close();
        }
        return fichadaOriginal;
    }
    /*
     * Selecionamos todos los registros a devolver
    */
    public ArrayList<FichadaOriginal> selectAll() {

        ArrayList<FichadaOriginal> lista = null;
        Cursor cursor = db.query(Tablas.TABLA_FICHADAS_ORIGINAL ,
                null, null, null, null, null , bcFichadaOriginal.FECHAHORA+ " ASC");

        if (cursor.moveToFirst())
        {

            do {
                FichadaOriginal fichadaOriginal = new FichadaOriginal();

                fichadaOriginal.setCodigo(cursor.getInt(0));
                fichadaOriginal.setEntSal(cursor.getInt(1));
                fichadaOriginal.setFechaHora(cursor.getString(2));
                fichadaOriginal.setExtra(cursor.getInt(3));
                if (cursor.getInt(1) == 0) {
                    fichadaOriginal.setIdDrawable(R.drawable.boton_rojo1);
                } else {
                    fichadaOriginal.setIdDrawable(R.drawable.boton_verde1);
                }

                lista.add( fichadaOriginal);

            }while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {//Se cierra el cursor si no estÃ¡ cerrado ya
            cursor.close();
        }
        return lista;
    }
    /*
       * Selecionamos todos los registros a devolver
      */
    public ArrayList<FichadaOriginal> selectPorMes(int Mes, int ano) {

        ArrayList<FichadaOriginal> lista = null;

        String cua="/01 00:00:00";
        String Fini = Integer.toString(ano)+"/"+Integer.toString(Mes)+cua;
        String FFin = Integer.toString(ano)+"/"+Integer.toString(Mes+1)+cua;

        Cursor cursor = db.rawQuery("Select * from " + Tablas.TABLA_FICHADAS_ORIGINAL + " where fechahora >= '" + Fini + "' and fechahora <= '" + FFin + "';", null);


        if (cursor.moveToFirst())
        {

            do {
                FichadaOriginal fichadaOriginal = new FichadaOriginal();

                fichadaOriginal.setCodigo(cursor.getInt(0));
                fichadaOriginal.setEntSal(cursor.getInt(1));
                fichadaOriginal.setFechaHora(cursor.getString(2));
                fichadaOriginal.setExtra(cursor.getInt(3));
                if (cursor.getInt(1) == 0) {
                    fichadaOriginal.setIdDrawable(R.drawable.boton_rojo1);
                } else {
                    fichadaOriginal.setIdDrawable(R.drawable.boton_verde1);
                }

                lista.add( fichadaOriginal);

            }while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {//Se cierra el cursor si no estÃ¡ cerrado ya
            cursor.close();
        }
        return lista;
    }
    public ArrayList<FichadaOriginal> selectPorDia(int dia, int Mes, int ano) {

        ArrayList<FichadaOriginal> lista = null;

        String cua1=" 00:00:00";
        String cua2=" 23:59:59";
        String Fini = Integer.toString(ano)+"/"+Integer.toString(Mes)+"/"+Integer.toString(dia)+cua1;
        String FFin = Integer.toString(ano)+"/"+Integer.toString(Mes)+"/"+Integer.toString(dia)+cua2;

        Cursor cursor = db.rawQuery("Select * from "+Tablas.TABLA_FICHADAS_ORIGINAL+" where fechahora >= '"+Fini+"' and fechahora <= '"+FFin+"';" , null );


        if (cursor.moveToFirst())
        {

            do {
                FichadaOriginal fichadaOriginal = new FichadaOriginal();

                fichadaOriginal.setCodigo(cursor.getInt(0));
                fichadaOriginal.setEntSal(cursor.getInt(1));
                fichadaOriginal.setFechaHora(cursor.getString(2));
                fichadaOriginal.setExtra(cursor.getInt(3));
                if (cursor.getInt(1) == 0) {
                    fichadaOriginal.setIdDrawable(R.drawable.boton_rojo1);
                } else {
                    fichadaOriginal.setIdDrawable(R.drawable.boton_verde1);
                }

                lista.add( fichadaOriginal);

            }while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {//Se cierra el cursor si no estÃ¡ cerrado ya
            cursor.close();
        }
        return lista;
    }
    public ArrayList<FichadaOriginal> selectPorFecha(String fecha1 , String fecha2) {

        ArrayList<FichadaOriginal> lista = null;
    Cursor cursor = db.rawQuery("Select * from "+Tablas.TABLA_FICHADAS_ORIGINAL+" where fechahora >= '"+fecha1+"' and fechahora <= '"+fecha2+"';" , null );


        if (cursor.moveToFirst())
        {

            do {
                FichadaOriginal fichadaOriginal = new FichadaOriginal();

                fichadaOriginal.setCodigo(cursor.getInt(0));
                fichadaOriginal.setEntSal(cursor.getInt(1));
                fichadaOriginal.setFechaHora(cursor.getString(2));
                fichadaOriginal.setExtra(cursor.getInt(3));
                if (cursor.getInt(1) == 0) {
                    fichadaOriginal.setIdDrawable(R.drawable.boton_rojo1);
                } else {
                    fichadaOriginal.setIdDrawable(R.drawable.boton_verde1);
                }

                lista.add( fichadaOriginal);

            }while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {//Se cierra el cursor si no estÃ¡ cerrado ya
            cursor.close();
        }
        return lista;
    }
    public ArrayList<FichadaOriginal> selectPorSemanaAno(int Ano , int Semana ) {
        
        Date poi= UtilsFechaHora.Num1DiaSemanaAno(Ano, Semana);

        String fecha1="";
        String fecha2="";

        String CadIni = " 00:00:00";
        String CadFin = " 23:59:59";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");


        try {

            fecha1 = formato.format( poi );
            fecha2 = formato.format( UtilsFechaHora.sumarRestarDiasFecha(poi ,6));

        } catch (Exception e){
            return null;
        }

        fecha1 = fecha1+CadIni;
        fecha2 = fecha2+CadFin;

        ArrayList<FichadaOriginal> lista = null;
        Cursor cursor = db.rawQuery("Select * from "+Tablas.TABLA_FICHADAS_ORIGINAL+" where fechahora >= '"+fecha1+"' and fechahora <= '"+fecha2+"';" , null );


        if (cursor.moveToFirst())
        {

            do {
                FichadaOriginal fichadaOriginal = new FichadaOriginal();

                fichadaOriginal.setCodigo(cursor.getInt(0));
                fichadaOriginal.setEntSal(cursor.getInt(1));
                fichadaOriginal.setFechaHora(cursor.getString(2));
                fichadaOriginal.setExtra(cursor.getInt(3));
                if (cursor.getInt(1) == 0) {
                    fichadaOriginal.setIdDrawable(R.drawable.boton_rojo1);
                } else {
                    fichadaOriginal.setIdDrawable(R.drawable.boton_verde1);
                }

                lista.add( fichadaOriginal);

            }while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {//Se cierra el cursor si no estÃ¡ cerrado ya
            cursor.close();
        }
        return lista;
    }
    /*
    public int insertMunicipio(Municipios muni){
        ContentValues values = new ContentValues();

        values.put(bcMunicipios.ID_PROVINCIAS, muni.getProvincia_id());
        values.put(bcMunicipios.MUNICIPIO     	, muni.getMunicipio());
        values.put(bcMunicipios.ID_MUNICIPIO			, muni.getId());
        values.put(bcMunicipios.LATITUD	, muni.getLatitud() );
        values.put(bcMunicipios.LONGITUD		, muni.getLongitud());


        int id = (int) db.insert(Tablas.TABLA_MUNICIPIOS, null, values);
        return id;
    }


    public void updateMunicipio(Municipios muni ) {
        ContentValues values = new ContentValues();
        values.put(bcMunicipios.ID_PROVINCIAS, muni.getProvincia_id());
        values.put(bcMunicipios.MUNICIPIO     	, muni.getMunicipio());
        values.put(bcMunicipios.ID_MUNICIPIO			, muni.getId());
        values.put(bcMunicipios.LATITUD	, muni.getLatitud() );
        values.put(bcMunicipios.LONGITUD		, muni.getLongitud());

        db.update(Tablas.TABLA_MUNICIPIOS, values, bcMunicipios.ID_MUNICIPIO+"=?", new String[] {Integer.toString( muni.getId())});
    }*/



}
