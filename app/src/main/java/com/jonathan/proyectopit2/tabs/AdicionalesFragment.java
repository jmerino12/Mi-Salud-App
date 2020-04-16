package com.jonathan.proyectopit2.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jonathan.proyectopit2.R;
import com.jonathan.proyectopit2.comunicacion.AdicionalesToDatos;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdicionalesFragment extends Fragment {
    private EditText adicionales;
    private EventBus bus =  EventBus.getDefault();
    public AdicionalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_adicionales, container, false);
        adicionales = view.findViewById(R.id.txtAdicionalesdatos);
        adicionales.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bus.post(new AdicionalesToDatos(adicionales.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return  view;
    }




}
