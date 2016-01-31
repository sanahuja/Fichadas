package com.fichadas.fichada;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class pagina3 extends Fragment {
    private CalendarView calendar;
    private View vista ;

    public pagina3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,    Bundle savedInstanceState) {

          vista =inflater.inflate(R.layout.fragment_pagina3, container, false);

        calendar = (CalendarView) vista.findViewById(R.id.calendarView);

        // sets whether to show the week number.
       try {
            calendar.setShowWeekNumber(true);
        }
        catch (Exception e)
        {
             Toast.makeText( vista.getContext() , "Error: "+e.toString(), Toast.LENGTH_LONG).show();
        }

        calendar.setShownWeekCount(1);


        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setSelectedWeekBackgroundColor( Color.GREEN ); //The background color for the selected week.
        calendar.setUnfocusedMonthDateColor(Color.BLUE); //sets the color for the dates of an unfocused month.
        calendar.setWeekSeparatorLineColor(Color.CYAN);        //sets the color for the separator line between weeks.
        calendar.setSelectedDateVerticalBar( R.color.darkgreen);         //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        calendar.setBackgroundColor( Color.LTGRAY);


        calendar.setWeekNumberColor( Color.RED );

        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText( vista.getContext() , day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
        // Inflate the layout for this fragment
        return vista;

    }



}
