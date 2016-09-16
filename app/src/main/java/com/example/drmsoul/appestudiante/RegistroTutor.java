package com.example.drmsoul.appestudiante;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RegistroTutor extends AppCompatActivity {

    Integer currentLvl = 0;
    private List<String> listCarrera = new ArrayList<String>();
    private Spinner spnCarrera;
    private Button btnRegistrar;
    private TextView txtNombreR, txtApellidoR, txtUsuarioR, txtPassR;
    String NR_id_usuario, NR_id_tutor;

    String ip = "http://logicmathematic.com/sw";
    String m_insert_usuario = ip+"/insert_usuario.php";
    String m_get_id_usuario = ip+"/get_id_usuario.php";
    String m_insert_perfil_usuario = ip+"/insert_perfil_usuario.php";
    String m_insert_tutor = ip+"/insert_tutor.php";
    String m_get_id_tutor = ip+"/get_id_tutor.php";
    String m_insert_tutor_horario = ip+"/get_id_tutor.php";

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
        serviceThread = new useService();
        String cont = m_get_id_usuario+"?usuario="+txtUsuarioR.getText()+"&clave="+txtPassR.getText();
        String cont2 = m_get_id_tutor+"?id_usuario=";
        serviceThread.execute(m_insert_usuario,
                "1",
                txtUsuarioR.getText().toString(),
                txtPassR.getText().toString(),
                cont,
                m_insert_perfil_usuario,
                "2",
                m_insert_tutor,
                txtNombreR.getText().toString(),
                txtApellidoR.getText().toString(),
                spnCarrera.getSelectedItem().toString(),
                m_insert_tutor_horario,
                m_get_id_tutor);

    }





    public class useService extends AsyncTask <String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            String newVAl = "";
            System.out.println("ingreso al servicio");
            String cadena = params[0];
            URL url = null;
            String devuelve ="";
            String usua = "";
            String tut = "";

            //AGREGAR USUARIO
            insertDatos(params[0], params[1], params[2], params[3], "", "");

            //BUSCAR USUARIO INGRESADO
             usua = consulta(params[4], "1");

            //AGREGAR TUTOR
            insertDatos(params[7], "3", usua, params[8], params[9], params[10]);

            //BUSCAR USUARIO INGRESADO
            tut = consulta(params[12], "2");




            return params[2]+ ", con ID: "+usua;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(), "Registro completado con exito : "+s, Toast.LENGTH_SHORT).show();


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        public void insertDatos(String cadena, String caso, String val1, String val2, String val3, String val4){

            try {
                HttpURLConnection urlConn;

                DataOutputStream printout;
                DataInputStream input;
                URL url = new URL(cadena);
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);
                urlConn.setRequestProperty("Content-Type", "application/json");
                urlConn.setRequestProperty("Accept", "application/json");
                urlConn.connect();
                //Creo el Objeto JSON
                JSONObject jsonParam = new JSONObject();

                if (caso.equals("1")) { //INSERT USUARIO
                    jsonParam.put("usuario", val1);
                    jsonParam.put("clave", val2);
                }

                else if(caso.equals("2")) { //INSERT PERFIL
                    jsonParam.put("id_usuario", val1);
                    jsonParam.put("id_perfil", val2);
                }
                else if(caso.equals("3")){
                    jsonParam.put("id_usuario", val1);
                    jsonParam.put("nombre", val2);
                    jsonParam.put("apellido", val3);
                    jsonParam.put("especialidad", val4);
                }
                else if(caso.equals("4")) { //INSERT PERFIL
                    jsonParam.put("id_tutor", val1);
                    jsonParam.put("id_horario", val2);
                }

                // Envio los parámetros post.
                OutputStream os = urlConn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(jsonParam.toString());
                writer.flush();
                writer.close();

                int respuesta = urlConn.getResponseCode();


                StringBuilder result = new StringBuilder();
                System.out.println("Prueba...");


                if (respuesta == HttpURLConnection.HTTP_OK) {
                    System.out.println("...Fin");

                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        result.append(line);
                        //response+=line;
                    }


                    //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                    JSONObject respuestaJSON = new JSONObject(result.toString());  //Creo un JSONObject a partir del StringBuilder pasado a cadena
                    //Accedemos al vector de resultados

                    String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                    System.out.println("EStado= "+resultJSON);
                    if (resultJSON.equals("1")) {      // hay un alumno que mostrar
                        System.out.println("Caso "+caso + " insertado correctamente");

                    } else if (resultJSON.equals("2")) {
                        System.out.println("Caso "+caso+ " pudo insertarse");
                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        public String consulta(String cadena, String caso){
            String resulS = "";

            try {
                URL url = new URL(cadena);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                //connection.setHeader("content-type", "application/json");

                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK){
                    //System.out.println("Ingreso aqui");

                    InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader


                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);        // Paso toda la entrada al StringBuilder
                    }

                    //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                    JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                    //Accedemos al vector de resultados

                    String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                    //System.out.println("EStado= "+resultJSON);

                    if (resultJSON.equals("1")){      // hay un alumno que mostrar
                        if (caso.equals("1")){
                            resulS = respuestaJSON.getJSONObject("usuario").getString("id");
                        }else if(caso.equals("2")){
                            resulS = respuestaJSON.getJSONObject("tutor").getString("id");
                        }

                    }
                    else if (resultJSON.equals("2")){
                        System.out.println("No existe el registro");
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return resulS;
        }



    }
}
