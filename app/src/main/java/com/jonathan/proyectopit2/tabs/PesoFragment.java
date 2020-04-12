package com.jonathan.proyectopit2.tabs;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.NumericWheelAdapter;
import com.contrarywind.view.WheelView;
import com.jonathan.proyectopit2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PesoFragment extends Fragment {

    public PesoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_peso, container, false);

        WheelView Kilo = view.findViewById(R.id.wheelviewKilo);
        WheelView Kilogramo =view.findViewById(R.id.wheelviewkilogramo);
        Kilo.setTextSize(30);
        Kilo.setDividerType(WheelView.DividerType.WRAP);
        Kilo.setTextColorCenter(Color.RED);
        Kilo.setDividerColor(Color.rgb(0,191,165));

        Kilogramo.setTextSize(30);
        Kilogramo.setDividerType(WheelView.DividerType.WRAP);
        Kilogramo.setTextColorCenter(Color.RED);
        Kilogramo.setDividerColor(Color.rgb(0,191,165));



        final List<Integer> Kilos = new ArrayList<>();
        for (int i = 40; i<= 250; i++){
            Kilos.add(i);
        }
        Kilogramo.setAdapter(new NumericWheelAdapter(0,9));
        ArrayWheelAdapter o =  new ArrayWheelAdapter(Kilos);
        Kilo.setAdapter(o);
        Kilogramo.setCurrentItem(0);
        Kilo.setCurrentItem(30);

        return view;
    }
}
