package com.example.drmsoul.appestudiante;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PantallaPrincipalEstudiante extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal_estudiante);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Menú Principal");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pantalla_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void horariosTutores(View v) {
        Intent intent = new Intent(context, horario_disponible.class);
        startActivity(intent);
    }
}
