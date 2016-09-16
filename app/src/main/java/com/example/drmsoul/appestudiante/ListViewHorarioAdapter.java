package com.example.drmsoul.appestudiante;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nathali on 12/08/16.
 */
public class ListViewHorarioAdapter extends BaseAdapter{


    private static ArrayList<ListViewHorarioItem> listHorario;

    private LayoutInflater mInflater;

    public ListViewHorarioAdapter(Context horariodisponibleFragment, ArrayList<ListViewHorarioItem> results) {
        listHorario = results;
        mInflater = LayoutInflater.from(horariodisponibleFragment);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listHorario.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listHorario.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.horario_item1, null);
            holder = new ViewHolder();
            holder.txthoraInicio = (TextView) convertView.findViewById(R.id.textViewHoraInicio);
            holder.txthoraFin = (TextView) convertView.findViewById(R.id.textViewHoraFin);
            holder.txtlugar = (TextView) convertView.findViewById(R.id.textViewLugar);
            holder.btn = (Button) convertView.findViewById(R.id.skipButton);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txthoraInicio.setText("Hora Inicio: "+listHorario.get(position).getHoraInicio());
        holder.txthoraFin.setText("Hora Fin:"+listHorario.get(position).getHoraFin());
        holder.txtlugar.setText("Lugar:"+listHorario.get(position).getLugar());
        holder.btn.setId(position);

        return convertView;
    }

    static class ViewHolder {
        TextView txthoraInicio,txthoraFin,txtlugar,btn;
    }


}
