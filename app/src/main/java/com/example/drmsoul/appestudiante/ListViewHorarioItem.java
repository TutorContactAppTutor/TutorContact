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

    private String horaInicio;
    private String horaFin;
    private String lugar;

    public ListViewHorarioItem() {
    }

    public ListViewHorarioItem(String horaInicio,String horaFin,String lugar) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lugar=lugar;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}