package com.example.drmsoul.appestudiante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class registro_opcion_perfil extends AppCompatActivity {

    private Button btnRegistroEstudiante, btnRegistroTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_opcion_perfil);

        btnRegistroEstudiante = (Button)findViewById(R.id.btnPerfilEstudiante);
        btnRegistroTutor= (Button)findViewById(R.id.btnPerfilTutor);

    }

    public void registraEstudiante(View v) {
        Intent intent = new Intent(this, registro.class);
        startActivity(intent);
    }

    public void registrarTutor(View v) {
        Intent intent = new Intent(this, RegistroTutor.class);
        startActivity(intent);
    }
}
