package com.fichadas.fichada;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.fichadas.clasesdb.FichadaOriginal;


import com.fichadas.utils.UtilsFechaHora;

import java.util.Arrays;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class pagina2 extends Fragment  implements GridView.OnItemClickListener ,AdapterView.OnItemSelectedListener  {
//    public class pagina2 extends Fragment implements GridView.OnItemClickListener , Spinner.OnItemSelectedListener {

    private GridView gridView1;
    private View vista;
    private Spinner cmbSemana;
    private Spinner cmbAnos;
    private ArrayAdapter<String> adapcmbSemana;
    private ArrayAdapter<String> adapcmbAnos;
    private  AdaptadorGridSemana adaptador;

    public pagina2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista = inflater.inflate(R.layout.fragment_pagina2, container, false);


        Date fechahoy = new Date();
        String sfechahoy  = DateFormat.format("yyyy/MM/dd HH:mm:ss", fechahoy.getTime()).toString();

        int nSemana = UtilsFechaHora.NumSemanaAno(sfechahoy);

        final String[] datosSemana =  new String[nSemana+1];
        int conta=nSemana;

        for ( int i=0; i  < nSemana ; i++ )
        {
            conta--;
            datosSemana[i] = "Semana : "+conta;
        }

        final String[] datosAnos =  new String[4];
        datosAnos[0]="Año : 2016";
        datosAnos[1]="Año : 2015";
        datosAnos[2]="Año : 2014";
        datosAnos[3]="Año : 2013";

        // Leer datos de la base de datos segun numero de semana

        adapcmbSemana =  new ArrayAdapter<String>( vista.getContext() , android.R.layout.simple_spinner_item, datosSemana);
        cmbSemana = (Spinner) vista.findViewById(R.id.xSemanas);
        adapcmbSemana.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbSemana.setAdapter(adapcmbSemana);
        cmbSemana.setOnItemSelectedListener(this);

        adapcmbAnos =  new ArrayAdapter<String>( vista.getContext() , android.R.layout.simple_spinner_item, datosAnos);
        cmbAnos = (Spinner) vista.findViewById(R.id.xAnos);
        adapcmbAnos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbAnos.setAdapter(adapcmbAnos);
        cmbAnos.setOnItemSelectedListener(this);



        // Apartir de aqui va el GRIDVIEW

        Arrays.sort( FichadaOriginal.ITEMS );

        adaptador = new AdaptadorGridSemana( vista.getContext() );
        gridView1 = (GridView) vista.findViewById(R.id.gridView);

        //Cogemos el grid para la cabecera
       // View header = View.inflate(  vista.getContext() , R.layout.cab_item, null);
       // gridView1.addHeaderView(header);
       // gridView1.setNumColumns(5);
        gridView1.setAdapter(adaptador);
        gridView1.setOnItemClickListener( this );

        return vista;
    }


   @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText( view.getContext() , "Posicion :"+position , Toast.LENGTH_LONG).show();

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(vista.getContext(), adapcmbSemana.getItem(position) ,Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
//OnItemSelectedListener