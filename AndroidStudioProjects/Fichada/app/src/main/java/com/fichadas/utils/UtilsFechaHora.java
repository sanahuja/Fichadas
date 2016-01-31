package com.fichadas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by informatica on 14/01/16.
 */
public class UtilsFechaHora {



    // Pasamos 2 Fecha y Obtenemos las diferencias en horas:minutos
    public static String diferenciaFechas(String inicio, String llegada){

        Date fechaInicio = null;
        Date fechaLlegada = null;

        // configuramos el formato en el que esta guardada la fecha en
        //  los strings que nos pasan
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            // aca realizamos el parse, para obtener objetos de tipo Date de
            // las Strings
            fechaInicio = formato.parse(inicio);
            fechaLlegada = formato.parse(llegada);

        } catch (ParseException e) {
            // Log.e(TAG, "Funcion diferenciaFechas: Error Parse " + e);
        } catch (Exception e){
            // Log.e(TAG, "Funcion diferenciaFechas: Error " + e);
        }

        // tomamos la instancia del tipo de calendario
        Calendar calendarInicio = Calendar.getInstance();
        Calendar calendarFinal = Calendar.getInstance();

        // Configramos la fecha del calendatio, tomando los valores del date que
        // generamos en el parse
        calendarInicio.setTime(fechaInicio);
        calendarFinal.setTime(fechaLlegada);

        // obtenemos el valor de las fechas en milisegundos
        long milisegundos1 = calendarInicio.getTimeInMillis();
        long milisegundos2 = calendarFinal.getTimeInMillis();

        // tomamos la diferencia
        long diferenciaMilisegundos = milisegundos2 - milisegundos1;

        // Despues va a depender en que formato queremos  mostrar esa
        // diferencia, minutos, segundo horas, dias, etc, aca van algunos
        // ejemplos de conversion

        // calcular la diferencia en segundos
        long diffSegundos =  Math.abs (diferenciaMilisegundos / 1000);

        // calcular la diferencia en minutos
        long diffMinutos =  Math.abs (diferenciaMilisegundos / (60 * 1000));
        long restominutos = diffMinutos%60;

        // calcular la diferencia en horas
        long diffHoras =   (diferenciaMilisegundos / (60 * 60 * 1000));

        // calcular la diferencia en dias
        long diffdias = Math.abs ( diferenciaMilisegundos / (24 * 60 * 60 * 1000) );

        // devolvemos el resultado en un string
        return String.valueOf(diffHoras + ":" + restominutos );
    }
    public static int NumSemanaAno(String date)
    {
        Date fecha = null;

        // configuramos el formato en el que esta guardada la fecha en
        //  los strings que nos pasan
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            // aca realizamos el parse, para obtener objetos de tipo Date de
            // las Strings
            fecha = formato.parse(date);

        } catch (ParseException e) {
            // Log.e(TAG, "Funcion diferenciaFechas: Error Parse " + e);
        } catch (Exception e){
            // Log.e(TAG, "Funcion diferenciaFechas: Error " + e);
        }

        // tomamos la instancia del tipo de calendario
        Calendar calendar = Calendar.getInstance();
        // Configramos la fecha del calendatio, tomando los valores del date que generamos en el parse
        calendar.setTime(fecha);

        calendar.setFirstDayOfWeek( Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek( 4 );
        calendar.setTime(fecha);
        int numberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);

        //calendar.set(Calendar.WEEK_OF_YEAR , 3);

        return numberWeekOfYear;
    }

    // De damos un Año y una semana y nos retorna el primer dia de esa semana --- Ejemplo le Damos 2016 y 3ª semana y nos devuelve 18/01/2016
    public static Date Num1DiaSemanaAno(int Ano, int nSemana)
    {
        String ano = Integer.toString(Ano);
        String date = ano+"/01/01 00:00:00";

        Date fecha = null;
                // configuramos el formato en el que esta guardada la fecha en
        //  los strings que nos pasan
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            // aca realizamos el parse, para obtener objetos de tipo Date de
            // las Strings
            fecha = formato.parse(date);

        } catch (ParseException e) {
            // Log.e(TAG, "Funcion diferenciaFechas: Error Parse " + e);
        } catch (Exception e){
            // Log.e(TAG, "Funcion diferenciaFechas: Error " + e);
        }

        // tomamos la instancia del tipo de calendario
        Calendar calendar = Calendar.getInstance();
        // Configramos la fecha del calendario, tomando los valores del date que generamos en el parse
        //calendar.setTime(fecha);

        calendar.setFirstDayOfWeek( Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek( 4 );
        calendar.setTime(fecha);

        String a112= calendar.getTime().toString();

        calendar.set(Calendar.WEEK_OF_YEAR , nSemana);
        String a1122= calendar.getTime().toString();
        calendar.setTime(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK ,2 );

        String a11222= calendar.getTime().toString();



        return calendar.getTime();
    }
    // Suma los días recibidos a la fecha

    public static Date sumarRestarDiasFecha(Date fecha, int dias)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }

    // Suma o resta las horas recibidos a la fecha
    public static Date sumarRestarHorasFecha(Date fecha, int horas)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.HOUR, horas);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }
}
