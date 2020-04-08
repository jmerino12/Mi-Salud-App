package com.jonathan.proyectopit2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jonathan.proyectopit2.controller.PagerController;
import com.jonathan.proyectopit2.tabs.AdicionalesFragment;
import com.jonathan.proyectopit2.tabs.PesoFragment;
import com.jonathan.proyectopit2.tabs.PresionFragment;


import java.util.Calendar;

public class DatosFragment extends Fragment {
    private TextInputEditText date,hora;
    private FirebaseAuth auth;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private DatabaseReference database;
    private TextView nombre;
    private TabLayout tab;
    private ViewPager viewPager;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_datos, container, false);
        hora = view.findViewById(R.id.hora);
        date = view.findViewById(R.id.fecha);
        nombre = view.findViewById(R.id.nombresDatos);
        tab = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.viewer);

        hora.setInputType(InputType.TYPE_NULL);
        date.setInputType(InputType.TYPE_NULL);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        obtenerInfoUsuario();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c= Calendar.getInstance();
                int horas=c.get(Calendar.HOUR_OF_DAY);
                int minutos=c.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora.setText(hourOfDay+":"+minute);
                    }
                },horas,minutos,false);
                timePickerDialog.show();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewerPager(viewPager);
        tab.setupWithViewPager(viewPager);
        tab.getTabAt(0).setIcon(R.drawable.ic_inspector);
        tab.getTabAt(0).setIcon(R.drawable.ic_atencion);
        tab.getTabAt(0).setIcon(R.drawable.ic_escala);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewerPager(ViewPager viewPager) {
        PagerController adapter = new PagerController(getChildFragmentManager(),0);

        adapter.addFragment(new PresionFragment(),"Presion");
        adapter.addFragment(new AdicionalesFragment(),"Adicionales");
        adapter.addFragment(new PesoFragment(),"Peso");

        viewPager.setAdapter(adapter);

    }

    private void obtenerInfoUsuario() {
        String id =auth.getCurrentUser().getUid();
        database.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nombres = dataSnapshot.child("nombres").getValue().toString();
                String correo = dataSnapshot.child("email").getValue().toString();
                String usuario = dataSnapshot.child("usuario").getValue().toString();
                String contra = dataSnapshot.child("contrasena").getValue().toString();
                String celular = dataSnapshot.child("celular").getValue().toString();
                nombre.setText(nombres);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
