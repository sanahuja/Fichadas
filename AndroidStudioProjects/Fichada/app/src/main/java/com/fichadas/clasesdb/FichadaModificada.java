package com.fichadas.clasesdb;
import com.fichadas.fichada.R;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by informatica on 26/01/16.
 */
public class FichadaModificada  implements Comparable<FichadaModificada> , Serializable {

    private int  codigo;
    private int  codigoOrigianal;
    private int  EntSal;
    private String   fechaHora;
    private int   Extra;
    private int idDrawable;

    public void FichadaModificada(){}

    public void FichadaModificada( int xcod ,int xcodor , int xes , String xfhora , int xextra ,  int xdibujo)
    {
        codigo = xcod ;
        codigoOrigianal = xcodor ;
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
    public int getCodigoOrigianal()
    {
        return codigoOrigianal;
    }
    public void setCodigoOrigianal(int xCodigo)
    {
        codigoOrigianal = xCodigo;
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
    public int compareTo(FichadaModificada o) {
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
    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return Fichada
     */
/*    public static FichadaOriginal getItem(int id) {
        for (FichadaOriginal item : ITEMS) {
            if (item.getCodigo() == id) {
                return item;
            }
        }
        return null;
    }
*/

}
