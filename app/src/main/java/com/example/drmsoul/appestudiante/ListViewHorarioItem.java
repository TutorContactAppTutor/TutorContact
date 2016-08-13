package com.example.drmsoul.appestudiante;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nathali on 12/08/16.
 */


public class ListViewHorarioItem{

    private String hora;
    private String lugar;

    public ListViewHorarioItem() {
    }

    public ListViewHorarioItem(String hora,String lugar) {
        this.hora = hora;
        this.lugar=lugar;
    }

    public String getHora() {
        return hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}