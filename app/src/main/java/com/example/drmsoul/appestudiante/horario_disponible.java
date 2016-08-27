package com.example.drmsoul.appestudiante;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

public class horario_disponible extends AppCompatActivity implements AdapterView.OnItemClickListener{

    final Context context = this;
    private ActionBarDrawerToggle mactionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView navList;

    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    private Fragment_Horario_Disponible fragmentoHorario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_disponible);


        fragmentoHorario = new Fragment_Horario_Disponible();

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
        getMenuInflater().inflate(R.menu.menu_pantalla_principal, menu);
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
        }

        return super.onOptionsItemSelected(item);

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        loadSelection(position);
        //drawerLayout.closeDrawer(navList);

    }
}