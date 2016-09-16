package com.example.drmsoul.appestudiante;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class horario_disponible extends AppCompatActivity implements AdapterView.OnItemClickListener{

    final Context context = this;
    private ActionBarDrawerToggle mactionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView navList;

    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    private Fragment_Horario_Disponible fragmentoHorario;

    private String lug;
    private String dlunes;

    String ip = "http://logicmathematic.com/sw";
    String m_insert_slot = ip+"/insert_slot.php";

    useService2 serviceThread1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_disponible);

        lug = getIntent().getExtras().getString("ParaNombre");

        fragmentoHorario = new Fragment_Horario_Disponible();
        fragmentoHorario.setNombreLugar(lug);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);

        navList = (ListView )findViewById(R.id.navlist);

        ArrayList<String> navArray = new ArrayList<String>();
        navArray.add("Lunes");
        navArray.add("Martes");
        navArray.add("Miércoles");
        navArray.add("Jueves");
        navArray.add("Viernes");
        navArray.add("Sábado");
        navArray.add("Domingo");

        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,navArray);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(this);

        mactionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.abrirfragment,R.string.cerrarfragment){

            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
            }

            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(mactionBarDrawerToggle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Horario Disponible");

        fragmentManager = getSupportFragmentManager();

        loadSelection(0);
        drawerLayout.openDrawer(navList);

    }

    private void loadSelection(int i){
        navList.setItemChecked(i,true);

        switch (i){
            case 0:

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,fragmentoHorario);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,fragmentoHorario);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,fragmentoHorario);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,fragmentoHorario);
                fragmentTransaction.commit();
                break;
            case 4:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,fragmentoHorario);
                fragmentTransaction.commit();
                break;
            case 5:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,fragmentoHorario);
                fragmentTransaction.commit();
                break;
            case 6:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,fragmentoHorario);
                fragmentTransaction.commit();
                break;
        }

    }
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mactionBarDrawerToggle.syncState();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tutor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==android.R.id.home){
            if(drawerLayout.isDrawerOpen(navList)){
                drawerLayout.closeDrawer(navList);
            }else{
                drawerLayout.openDrawer(navList);
            }
        }else if(id==R.id.cerrar){
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.regresar1){
            Intent intent = new Intent(context, PantallaPrincipalTutor.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        loadSelection(position);
        //drawerLayout.closeDrawer(navList);

    }


    public void guardarHorario(View w){

        switch(w.getId()) {
            case 0:
                serviceThread1 = new useService2();
                String cont = m_insert_slot+"?id_tutor_horario=2&hora_inicio=08h30&hora_fin=09h30&lugar="+lug+"&estado_slot=1&estado_reserva=0";

                serviceThread1.execute(cont, "1");

                System.out.println("hola1");
                break;
            case 1:
                String cont2 = m_insert_slot+"?id_tutor_horario=2&hora_inicio=09h30&hora_fin=10h30&lugar="+lug+"&estado_slot=1&estado_reserva=0";

                serviceThread1.execute(cont2, "1");
                break;
            case 2:
                String cont3 = m_insert_slot+"?id_tutor_horario=2&hora_inicio=10h30&hora_fin=11h30&lugar="+lug+"&estado_slot=1&estado_reserva=0";

                serviceThread1.execute(cont3, "1");
                break;
            case 3:
                String cont4 = m_insert_slot+"?id_tutor_horario=2&hora_inicio=11h30&hora_fin=12h30&lugar="+lug+"&estado_slot=1&estado_reserva=0";

                serviceThread1.execute(cont4, "1");
                break;
            case 4:
                String cont5 = m_insert_slot+"?id_tutor_horario=2&hora_inicio=12h30&hora_fin=13h30&lugar="+lug+"&estado_slot=1&estado_reserva=0";

                serviceThread1.execute(cont5, "1");
                break;
            case 5:

                String cont6 = m_insert_slot+"?id_tutor_horario=2&hora_inicio=13h30&hora_fin=14h30&lugar="+lug+"&estado_slot=1&estado_reserva=0";

                serviceThread1.execute(cont6, "1");
                break;
            case 6:
                String cont7 = m_insert_slot+"?id_tutor_horario=2&hora_inicio=14h30&hora_fin=15h30&lugar="+lug+"&estado_slot=1&estado_reserva=0";

                serviceThread1.execute(cont7, "1");
                break;
            case 7:
                String cont8 = m_insert_slot+"?id_tutor_horario=2&hora_inicio=15h30&hora_fin=16h30&lugar="+lug+"&estado_slot=1&estado_reserva=0";

                serviceThread1.execute(cont8, "1");
                break;
        }
    }


    public class useService2 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            System.out.println("ingreso al servicioa");
            String cadena = params[0];
            URL url = null;
            String devuelve ="";

            if (params[1] == "1"){  // INSERT slot



            }else if (params[1] == "2"){ // RETURN slot
                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                    //connection.setHeader("content-type", "application/json");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK){
                        System.out.println("Ingreso aqui");

                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                        // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                        // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                        // StringBuilder.

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                        System.out.println("EStado= "+resultJSON);

                        if (resultJSON.equals("1")){      // hay un alumno que mostrar
                            devuelve = devuelve + respuestaJSON.getJSONObject("usuario").getString("id") ;
                            System.out.println("Respuesta= "+resultJSON);
                        }
                        else if (resultJSON.equals("2")){
                            devuelve = "No existe el Usuario";
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;



            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(getApplicationContext(), "ID encontrada: "+s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }


    }
}