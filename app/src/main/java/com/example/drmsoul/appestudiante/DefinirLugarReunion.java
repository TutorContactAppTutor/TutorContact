package com.example.drmsoul.appestudiante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.grupotutor.tutorcontact.MainActivity;

public class DefinirLugarReunion extends AppCompatActivity {

    EditText enviarNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definir_lugar_reunion);

        enviarNombre = (EditText)findViewById(R.id.infNombre);
    }

    public void IraHorarioDisponible(View v) {

        Intent intent = new Intent(DefinirLugarReunion.this,horario_disponible.class);
        intent.putExtra("ParaNombre",enviarNombre.getText().toString());
        startActivity(intent);

    }
}
