package com.jonathan.proyectopit2.tabs;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.jonathan.proyectopit2.R;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class PresionFragment extends Fragment {

    public PresionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_presion, container, false);

        WheelView wheelViewSistolic = view.findViewById(R.id.wheelviewSistolica);
        WheelView wheelViewSistolic2 =view.findViewById(R.id.wheelviewSistolica2);
        wheelViewSistolic.setTextSize(20);
        wheelViewSistolic.setLineSpacingMultiplier(2f);
        wheelViewSistolic.setDividerType(WheelView.DividerType.CIRCLE);
        wheelViewSistolic.setTextColorCenter(Color.RED);
        wheelViewSistolic.setDividerColor(Color.rgb(0,191,165));

        wheelViewSistolic2.setTextSize(20);
        wheelViewSistolic2.setLineSpacingMultiplier(2f);
        wheelViewSistolic2.setDividerType(WheelView.DividerType.CIRCLE);
        wheelViewSistolic2.setTextColorCenter(Color.RED);
        wheelViewSistolic2.setDividerColor(Color.rgb(0,191,165));




        final List<String> mOptionsItems = new ArrayList<>();
        mOptionsItems.add("10");
        mOptionsItems.add("20");
        mOptionsItems.add("30");
        mOptionsItems.add("40");
        mOptionsItems.add("50");
        mOptionsItems.add("60");
        mOptionsItems.add("70");
        wheelViewSistolic.setAdapter(new ArrayWheelAdapter(mOptionsItems));
        wheelViewSistolic2.setAdapter(new ArrayWheelAdapter(mOptionsItems));



        return view;
    }
}
