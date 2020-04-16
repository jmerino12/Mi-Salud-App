package com.jonathan.proyectopit2.tabs;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.jonathan.proyectopit2.DatosFragment;
import com.jonathan.proyectopit2.R;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class PresionFragment extends Fragment {
    private WheelView wheelViewSistolic,wheelViewDiastolica;
    private List<Integer> Items = new ArrayList<>();
    private String sistolica,diastolica;
    public PresionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_presion, container, false);

        wheelViewSistolic = view.findViewById(R.id.wheelviewSistolica);
        wheelViewDiastolica =view.findViewById(R.id.wheelviewDisatolica);

        wheelViewSistolic.setTextSize(30);
        wheelViewSistolic.setDividerWidth(2);
        wheelViewSistolic.setDividerType(WheelView.DividerType.WRAP);
        wheelViewSistolic.setTextColorCenter(Color.RED);
        wheelViewSistolic.setDividerColor(Color.rgb(0,191,165));

        wheelViewDiastolica.setTextSize(30);
        wheelViewDiastolica.setDividerType(WheelView.DividerType.WRAP);
        wheelViewDiastolica.setTextColorCenter(Color.GREEN);
        wheelViewDiastolica.setDividerColor(Color.rgb(0,191,165));
        wheelViewDiastolica.setDividerWidth(2);

        Items = new ArrayList<>();
        for (int i = 40; i<= 250; i++){
            Items.add(i);
        }
        final ArrayWheelAdapter o =  new ArrayWheelAdapter(Items);
        wheelViewSistolic.setAdapter(o);
        wheelViewDiastolica.setAdapter(o);
        wheelViewSistolic.setCurrentItem(80);
        wheelViewDiastolica.setCurrentItem(40);

        Bundle datosAEnviar = new Bundle();
        wheelViewSistolic.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                sistolica = o.getItem(index).toString();
            }
        });
        wheelViewDiastolica.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                diastolica = o.getItem(index).toString();
            }
        });


        return view;
    }
}
