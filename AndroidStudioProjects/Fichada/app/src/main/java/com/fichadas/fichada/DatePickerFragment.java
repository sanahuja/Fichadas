package com.fichadas.fichada;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by informatica on 13/01/16.
 */
public class DatePickerFragment extends DialogFragment  implements DatePickerDialog.OnDateSetListener {

    public String Fecha="2016/01/01";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String ano = Integer.toString( year  );
        String mes = Integer.toString( month );
        String dia = Integer.toString( day   );

        Fecha=ano+"/"+mes+"/"+dia;


        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do smething with the date chosen by the user
        String ano = Integer.toString( year  );
        String mes = Integer.toString( month+1 );
        String dia = Integer.toString( day   );

        if (mes.length()==1)
            mes="0"+mes;

        if (dia.length()==1)
            dia="0"+dia;




        Fecha=ano+"/"+mes+"/"+dia;
        ((TextView) getActivity().findViewById(R.id.txtFecha)).setText( Fecha );


    }
}