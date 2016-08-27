package com.example.drmsoul.appestudiante;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.drmsoul.appestudiante.R;

import java.util.ArrayList;

public class Fragment_Horario_Disponible extends Fragment {

    private static Handler mainUIHandler;
    private static Handler bgThreadHandler;
    private HiloSegundoPlano hilo1;
    private int count;
    Runnable r,r1;

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

        r = new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                count++;

                if (bgThreadHandler != null) {



                    //System.out.println("Enviando horario escogido");
                    Toast.makeText(getActivity(),"Enviando horario escogido",Toast.LENGTH_SHORT).show();

                    bgThreadHandler.post(r);
                }
            }
        };


        return rootView;
    }

    private ArrayList<ListViewHorarioItem> GetlistaHorario(){
        ArrayList<ListViewHorarioItem> listHorarios = new ArrayList<ListViewHorarioItem>();

        ListViewHorarioItem itemHorario = new ListViewHorarioItem();

        itemHorario.setHoraInicio("08h30");
        itemHorario.setHoraFin("09h30");
        itemHorario.setLugar("Lugar:");
        listHorarios.add(itemHorario);

        itemHorario = new ListViewHorarioItem();
        itemHorario.setHoraInicio("09h30");
        itemHorario.setHoraFin("10h30");
        itemHorario.setLugar("Lugar:");
        listHorarios.add(itemHorario);

        itemHorario = new ListViewHorarioItem();
        itemHorario.setHoraInicio("10h30");
        itemHorario.setHoraFin("11h30");
        itemHorario.setLugar("Lugar:");
        listHorarios.add(itemHorario);

        itemHorario = new ListViewHorarioItem();
        itemHorario.setHoraInicio("11h30");
        itemHorario.setHoraFin("12h30");
        itemHorario.setLugar("Lugar:");
        listHorarios.add(itemHorario);

        itemHorario = new ListViewHorarioItem();
        itemHorario.setHoraInicio("12h30");
        itemHorario.setHoraFin("13h30");
        itemHorario.setLugar("Lugar:");
        listHorarios.add(itemHorario);

        itemHorario = new ListViewHorarioItem();
        itemHorario.setHoraInicio("13h30");
        itemHorario.setHoraFin("14h30");
        itemHorario.setLugar("Lugar:");
        listHorarios.add(itemHorario);

        itemHorario = new ListViewHorarioItem();
        itemHorario.setHoraInicio("14h30");
        itemHorario.setHoraFin("15h30");
        itemHorario.setLugar("Lugar:");
        listHorarios.add(itemHorario);

        itemHorario = new ListViewHorarioItem();
        itemHorario.setHoraInicio("15h30");
        itemHorario.setHoraFin("16h30");
        itemHorario.setLugar("Lugar:");
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

    public void guardarHilo(View v){

        mainUIHandler = new Handler();
        mainUIHandler.post(r);

        hilo1 = new HiloSegundoPlano(mainUIHandler);
        hilo1.start();
    }



    public class HiloSegundoPlano extends Thread{

        Handler uiHandler;

        public HiloSegundoPlano(Handler uihandler1) {
            uiHandler = uihandler1;

        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            this.setName(HiloSegundoPlano.class.getName());
            this.setPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
            // Looper must be prepared before creating handler for background
            // thread
            Looper.prepare();

            bgThreadHandler = new Handler();


            r1 = new Runnable() {
                @Override
                public void run() {

                    try {
                        // Perform some task that need to be updated to UI
                        // thread after completion
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //count++;

                    if (uiHandler != null) {


                        //System.out.println("Horario guardado en la base");
                        Toast.makeText(getActivity(),"Horario guardado en la base",Toast.LENGTH_SHORT).show();

                        //uiHandler.post(r1);


                    }

                }
            };

            bgThreadHandler.post(r1);

            // Run the message queue in this thread call Looper.loop()
            Looper.loop();
        }
    }

}
