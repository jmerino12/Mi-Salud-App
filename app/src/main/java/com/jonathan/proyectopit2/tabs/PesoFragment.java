package com.jonathan.proyectopit2.tabs;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.NumericWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.jonathan.proyectopit2.R;
import com.jonathan.proyectopit2.comunicacion.AdicionalesToDatos;
import com.jonathan.proyectopit2.comunicacion.PesoToDatos;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PesoFragment extends Fragment {
    private WheelView Kilo,Kilogramo;
    private EventBus bus =  EventBus.getDefault();
    final List<Integer> Kilos = new ArrayList<>();
    final List<Integer> Kilogramoos = new ArrayList<>();
    int pesoDerecho, pesoIzquierdo;
    private String Aux;
    public PesoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_peso, container, false);

        Kilo = view.findViewById(R.id.wheelviewKilo);
        Kilogramo =view.findViewById(R.id.wheelviewkilogramo);
        Kilo.setTextSize(30);
        Kilo.setDividerType(WheelView.DividerType.WRAP);
        Kilo.setTextColorCenter(Color.RED);
        Kilo.setDividerColor(Color.rgb(0,191,165));

        Kilogramo.setTextSize(30);
        Kilogramo.setDividerType(WheelView.DividerType.WRAP);
        Kilogramo.setTextColorCenter(Color.RED);
        Kilogramo.setDividerColor(Color.rgb(0,191,165));




        for (int i = 40; i<= 250; i++){
            Kilos.add(i);
        }
        for (int j = 0; j<= 9;j++){
            Kilogramoos.add(j);
        }

        final ArrayWheelAdapter o =  new ArrayWheelAdapter(Kilos);
        final ArrayWheelAdapter p =  new ArrayWheelAdapter(Kilogramoos);
        Kilo.setAdapter(o);
        Kilogramo.setAdapter(p);
        Kilogramo.setCurrentItem(0);
        Kilo.setCurrentItem(30);
        Kilo.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                pesoDerecho = (int) o.getItem(index);
            }
        });
        Kilogramo.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                pesoIzquierdo = (int) p.getItem(index);
                Aux = pesoDerecho+"."+pesoIzquierdo;
                Toast.makeText(getActivity(),""+Aux,Toast.LENGTH_LONG).show();
                bus.post(new PesoToDatos(Aux));
            }
        });

        return view;
    }
}
