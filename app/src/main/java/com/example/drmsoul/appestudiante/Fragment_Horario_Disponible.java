package com.example.drmsoul.appestudiante;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class Fragment_Horario_Disponible extends Fragment {
    public Fragment_Horario_Disponible() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_horario, container, false);

        View rootView = inflater.inflate(R.layout.fragment_horario, container, false);

        ArrayList<ListViewHorarioItem> listaHorario = GetlistaHorario();
        ListView lv = (ListView)rootView.findViewById(R.id.horariolist);
        lv.setAdapter(new ListViewHorarioAdapter(getActivity(), listaHorario));

        return rootView;
    }

    private ArrayList<ListViewHorarioItem> GetlistaHorario(){
        ArrayList<ListViewHorarioItem> listHorarios = new ArrayList<ListViewHorarioItem>();

        ListViewHorarioItem itemHorario = new ListViewHorarioItem();

        itemHorario.setHora("08h30-09h30");
        itemHorario.setLugar("C-301");
        listHorarios.add(itemHorario);

        itemHorario = new ListViewHorarioItem();
        itemHorario.setHora("09h30-10h30");
        itemHorario.setLugar("C-302");
        listHorarios.add(itemHorario);

        itemHorario = new ListViewHorarioItem();
        itemHorario.setHora("10h30-11h30");
        itemHorario.setLugar("C-303");
        listHorarios.add(itemHorario);

        return listHorarios;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void onDestroy(){
        super.onDestroy();
    }
}
