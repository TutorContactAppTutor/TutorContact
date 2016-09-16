package com.example.drmsoul.appestudiante;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class registro extends AppCompatActivity {

    private List<String> listCarrera = new ArrayList<String>();
    private Spinner spnCarrera;
    private Button btnRegistrar;

    private TextView txtUser, txtPassword, txtUsuarioR, txtTelf;

    String ip = "http://logicmathematic.com/sw";
    String m_insert_usuario = ip+"/insert_usuario.php";
    String m_get_id_usuario = ip+"/get_id_usuario.php";
    String m_insert_perfil_usuario = ip+"/insert_perfil_usuario.php";
    String m_insert_tutor = ip+"/insert_tutor.php";
    String m_get_id_tutor = ip+"/get_id_tutor.php.php";
    String m_insert_tutor_horario = ip+"/get_id_tutor.php.php";

    useService serviceThread;


      public void registrarTutor(View v){
        txtUser = (TextView)findViewById(R.id.txtNombre);
        txtPassword = (TextView)findViewById(R.id.txtApellido);
        txtUsuarioR = (TextView)findViewById(R.id.txtUsuario);
        txtTelf= (TextView)findViewById(R.id.txtPassword);

        //EMPIEZA PROCESO DE REGISTRO********************************************************


        //----------------Guardar usuario----------------------------
        //serviceThread = new useService();
        //serviceThread.execute(m_insert_usuario, "1");
        //--------------Consultar nuevo usuario---------------------
        serviceThread = new useService();
        String cont = m_get_id_usuario+"?usuario="+txtUser.getText()+"&clave="+txtPassword.getText();
        System.out.println("url enviada "+cont);
        serviceThread.execute(cont, "2");


        //Toast.makeText(getApplicationContext(), txtUsuarioR.getText() + ", su información ha sido guardada. Docente de "+spnCarrera.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        btnRegistrar = (Button)findViewById(R.id.regbutton);
    }



    public void registrarEstudiante(View v){
         txtUser = (TextView)findViewById(R.id.emailTxt);
        txtPassword = (TextView)findViewById(R.id.passTxt);
        txtUsuarioR = (TextView)findViewById(R.id.nombreTxt);
        txtTelf = (TextView)findViewById(R.id.telTxt);

        //EMPIEZA PROCESO DE REGISTRO********************************************************


        //----------------Guardar usuario----------------------------
        //serviceThread = new useService();
        //serviceThread.execute(m_insert_usuario, "1");
        //--------------Consultar nuevo usuario---------------------

        String cont = m_get_id_usuario+"?usuario="+txtUsuarioR.getText()+"&clave="+ txtTelf.getText();
        System.out.println("url enviada: "+cont);
        serviceThread.execute(cont, "2");


        //Toast.makeText(getApplicationContext(), txtUsuarioR.getText() + ", su información ha sido guardada. Docente de "+spnCarrera.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
    }

    public class useService extends AsyncTask<String,Void,String> {

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
