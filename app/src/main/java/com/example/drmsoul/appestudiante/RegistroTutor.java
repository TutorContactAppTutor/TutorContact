package com.example.drmsoul.appestudiante;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RegistroTutor extends AppCompatActivity {


    private List<String> listCarrera = new ArrayList<String>();
    private Spinner spnCarrera;
    private Button btnRegistrar;
    private TextView txtNombreR, txtApellidoR, txtUsuarioR, txtPassR;

    String ip = "http://logicmathematic.com/sw";
    String m_insert_usuario = ip+"insert_usuario.php";
    String m_get_id_usuario = ip+"/get_id_usuario.php";
    String m_insert_perfil_usuario = ip+"insert_perfil_usuario.php";
    String m_insert_tutor = ip+"insert_tutor.php";
    String m_get_id_tutor = ip+"get_id_tutor.php.php";
    String m_insert_tutor_horario = ip+"get_id_tutor.php.php";

    useService serviceThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tutor);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Registrarse");

        listCarrera.add("Especialidad");
        listCarrera.add("Tutoria para LIPRO");
        listCarrera.add("Tutoria para LIGRA");
        listCarrera.add("Tutoria para LICWEB");
        listCarrera.add("Tutoria para LICCOM");

        spnCarrera = (Spinner)findViewById(R.id.spnCarrera);
        btnRegistrar = (Button)findViewById(R.id.btnRegistro);



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listCarrera) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View v = null;

                // Hide first position on scroll
                if (position == 0) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                }
                else {
                    // Pass convertView as null to prevent reuse of special case views
                    v = super.getDropDownView(position, null, parent);
                }

                // Hide scroll bar because it appears sometimes unnecessarily, this does not prevent scrolling
                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCarrera.setAdapter(dataAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.salir:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void registrarTutor(View v){
        txtNombreR = (TextView)findViewById(R.id.txtNombre);
        txtApellidoR = (TextView)findViewById(R.id.txtApellido);
        txtUsuarioR = (TextView)findViewById(R.id.txtUsuario);
        txtPassR = (TextView)findViewById(R.id.txtPassword);

        //EMPIEZA PROCESO DE REGISTRO********************************************************


        //----------------Guardar usuario----------------------------
        //serviceThread = new useService();
        //serviceThread.execute(m_insert_usuario, "1");
        //--------------Consultar nuevo usuario---------------------
        serviceThread = new useService();
        String cont = m_get_id_usuario+"?usuario="+txtUsuarioR.getText()+"&clave="+txtPassR.getText();
        System.out.println("url enviada "+cont);
        serviceThread.execute(cont, "2");


        //Toast.makeText(getApplicationContext(), txtUsuarioR.getText() + ", su información ha sido guardada. Docente de "+spnCarrera.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
    }

    public class useService extends AsyncTask <String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            System.out.println("ingreso al servicioa");
            String cadena = params[0];
            URL url = null;
            String devuelve ="";

            if (params[1] == "1"){  // INSERT USUARIO

            }else if (params[1] == "2"){ // RETURN USUARIO NUEVO
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
            Toast.makeText(getApplicationContext(), "ID encontrada: "+s, Toast.LENGTH_SHORT).show();
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
