package com.example.drmsoul.appestudiante;

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

import java.util.ArrayList;
import java.util.List;

public class RegistroTutor extends AppCompatActivity {


    private List<String> listCarrera = new ArrayList<String>();
    private Spinner spnCarrera;
    private Button btnRegistrar;
    private TextView txtNombreR, txtApellidoR, txtUsuarioR, txtPassR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tutor);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Registrarse");

        listCarrera.add("¿De qué carrera es tutor?");
        listCarrera.add("LIPRO");
        listCarrera.add("LIGRA");
        listCarrera.add("LICWEB");
        listCarrera.add("LICCOM");

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

        Toast.makeText(getApplicationContext(), txtUsuarioR.getText() + ", su información ha sido guardada.", Toast.LENGTH_SHORT).show();
    }
}
