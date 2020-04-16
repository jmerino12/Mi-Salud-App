package com.jonathan.proyectopit2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jonathan.proyectopit2.adapters.RegistrosAdapter;
import com.jonathan.proyectopit2.model.Presion;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class RegistroFragment extends Fragment {
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private RegistrosAdapter rAdapter;
    private DatabaseReference mDatabase;
    private ArrayList<Presion> arrayList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_registro, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth =  FirebaseAuth.getInstance();
        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getDatos();
        return view;
    }

    private void getDatos(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Presion").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        String hora = ds.child("hora").getValue().toString();
                        String peso = ds.child("peso").getValue().toString();
                        String adicionales = ds.child("adicionales").getValue().toString();
                        String presionDiastolica = ds.child("presionDiastolica").getValue().toString();
                        String presionSiastolica = ds.child("presionSistolica").getValue().toString();
                        String fecha = ds.child("fecha").getValue().toString();
                        arrayList.add(new Presion(fecha,hora,presionDiastolica,presionSiastolica,adicionales,peso));
                    }
                    rAdapter =  new RegistrosAdapter(arrayList,R.layout.datos);
                    recyclerView.setAdapter(rAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

