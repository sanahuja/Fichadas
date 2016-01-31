package com.fichadas.clasesdb;

import com.fichadas.fichada.R;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by informatica on 14/01/16.
 */
public class FichadaOriginal implements Comparable<FichadaOriginal> , Serializable {
    private int  codigo;
    private int  EntSal;
    private String   fechaHora;
    private int   Extra;
    private int idDrawable;

    public FichadaOriginal(){}

   public FichadaOriginal(int xcod , int xes , String xfhora , int xextra , int xdibujo)
    {
        codigo = xcod ;
        EntSal = xes ;
        fechaHora = xfhora ;
        Extra = xextra ;
        idDrawable =xdibujo ;
    }
    public int getCodigo()
    {
        return codigo;
    }
    public void setCodigo(int xCodigo)
    {
        codigo = xCodigo;
    }

    public int getEntSal()
    {
        return EntSal;
    }
    public void setEntSal(int xEntSal)
    {
        EntSal = xEntSal;
    }
    public String getFechaHora()
    {
        return fechaHora;
    }
    public void setFechaHora(String xFechaHora)
    {
        fechaHora = xFechaHora;
    }
    public int getExtra()
    {
        return Extra;
    }
    public void setExtra(int xExtra)
    {
        Extra = xExtra;
    }
    public int getIdDrawable()
    {
        return idDrawable;
    }
    public void setIdDrawable(int xdibujo)
    {
        idDrawable = xdibujo;
    }

    @Override
    public int compareTo(FichadaOriginal o) {
        Date fecxa1 = null;
        Date fecxa2 = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            // aca realizamos el parse, para obtener objetos de tipo Date de
            // las Strings
            fecxa1 = formato.parse(fechaHora);
            fecxa2 = formato.parse(o.fechaHora);


        } catch (ParseException e) {
            //
        } catch (Exception e){
            //
        }


        if ( fecxa1.before( fecxa2)) {
            return -1;
        }
        if (fecxa2.before( fecxa1)) {
            return 1;
        }
        return 0;
    }


    public static FichadaOriginal[] ITEMS = {
            new FichadaOriginal( 1 , 0 , "2016/01/01 09:00:00" , 0 , R.drawable.boton_rojo1) ,
            new FichadaOriginal( 2 , 1 , "2016/01/01 12:00:00" , 0 ,  R.drawable.boton_verde1) ,
            new FichadaOriginal( 3 , 0 , "2016/01/01 14:00:00" , 0 , R.drawable.boton_rojo1) ,
            new FichadaOriginal( 4 , 1, "2016/01/01 19:00:00" , 0 ,   R.drawable.boton_verde1),
            new FichadaOriginal( 9 , 0 , "2016/01/03 07:00:00" , 0 , R.drawable.boton_rojo1) ,
            new FichadaOriginal( 10 , 1  , "2016/01/03 12:00:00" , 0 ,  R.drawable.boton_verde1),
            new FichadaOriginal( 5 , 0 , "2016/01/02 09:00:00" , 0 , R.drawable.boton_rojo1) ,
            new FichadaOriginal( 6 , 1 , "2016/01/02 12:00:00" , 0 ,  R.drawable.boton_verde1) ,
            new FichadaOriginal( 7 , 0 , "2016/01/02 14:00:00" , 0 , R.drawable.boton_rojo1) ,
            new FichadaOriginal( 8 , 1 , "2016/01/02 19:00:00" , 0 ,   R.drawable.boton_verde1)

    };

    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return FichadaOriginal
     */
    public static FichadaOriginal getItem(int id) {
        for (FichadaOriginal item : ITEMS) {
            if (item.getCodigo() == id) {
                return item;
            }
        }
        return null;
    }


}

///http://www.hermosaprogramacion.com/2015/07/tutorial-para-crear-un-gridview-en-android/