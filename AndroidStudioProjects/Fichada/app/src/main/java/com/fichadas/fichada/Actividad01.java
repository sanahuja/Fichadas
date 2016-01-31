package com.fichadas.fichada;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.fichadas.clasesdb.DbLite;
import com.fichadas.utils.UtilsFechaHora;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Actividad01 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public String xFecha = "";
    public String xHora = "";
    public Integer xEntradaSalida = 0;
    public Integer xTipoHoras = 0;
    private Toolbar toolbar = null;
    private NavigationView navigationView = null;
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad01);

        mp = MediaPlayer.create(this, R.raw.click);


        pagina1 fragment = new pagina1();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.pagina1, fragment);
        fragmentTransaction.commit();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actividad01, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_camera) {
            pagina1 fragment = new pagina1();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.pagina1, fragment);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_gallery) {
            pagina2 fragment = new pagina2();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.pagina1, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_slideshow) {
            pagina3 fragment = new pagina3();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.pagina1, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_manage) {
            pagina4 fragment = new pagina4();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.pagina1, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_share) {

            Toast.makeText(getApplicationContext(), "Compartir", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_send) {

            Toast.makeText(getApplicationContext(), "Enviar", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void pulsacion(View c) {


        mp.start();

        Toast.makeText(getApplicationContext(), "Entrada", Toast.LENGTH_LONG).show();

        DbLite usdbh = new DbLite(this );

        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if (db != null) {

            TextView pp1 = (TextView) findViewById(R.id.txtFecha);
            TextView pp2 = (TextView) findViewById(R.id.txtHora);

            xFecha = pp1.getText().toString();
            xHora = pp2.getText().toString();
            ;

            xEntradaSalida = 0;
            xTipoHoras = 0;


            Cursor cDatos = db.rawQuery("SELECT entrada , fechahora FROM fichadas WHERE codigo = (select max(codigo) FROM fichadas)", null);
            int ccodigo = 3;
            String fechah = "";

            if (cDatos.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    ccodigo = cDatos.getInt(0);
                    fechah = cDatos.getString(1);

                    break;
                } while (cDatos.moveToNext());
            }

            if (ccodigo == 0) {
                xEntradaSalida = 1;  // Salida
                String dif = UtilsFechaHora.diferenciaFechas(fechah, xFecha + " " + xHora);

                Toast.makeText(Actividad01.this, "Salida Horas:" + dif.toString(), Toast.LENGTH_SHORT).show();
            } else {
                xEntradaSalida = 0; // Entrada
                Toast.makeText(Actividad01.this, "Entrada", Toast.LENGTH_SHORT).show();
            }

            //Insertamos los datos en la tabla Usuarios
            db.execSQL("INSERT INTO fichadas ( entrada , fechahora , extra) VALUES (" + xEntradaSalida.toString() + ", '" + xFecha + " " + xHora + "' , " + xTipoHoras + " )");


            //Cerramos la base de datos
            db.close();
        }

    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void pulsasemana(View c) {

        CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        Date fecha= new Date(cv.getDate());



        Date poi= UtilsFechaHora.Num1DiaSemanaAno( 2016 , 3);

        Toast.makeText(Actividad01.this, "Salida :" + poi.toString(), Toast.LENGTH_SHORT).show();

    }

}
