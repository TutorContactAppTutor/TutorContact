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
            convertView = mInflater.inflate(R.layout.horario_item, null);
            holder = new ViewHolder();
            holder.txthora = (TextView) convertView.findViewById(R.id.textViewHora);
            holder.txtlugar = (TextView) convertView.findViewById(R.id.textViewLugar);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txthora.setText(listHorario.get(position).getHora());
        holder.txtlugar.setText(listHorario.get(position).getLugar());

        return convertView;
    }

    static class ViewHolder {
        TextView txthora, txtlugar;
    }
}
