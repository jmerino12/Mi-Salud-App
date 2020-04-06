package com.jonathan.proyectopit2;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoUsuariosFragment extends Fragment {
    TextView fullNombre, fullcorreo;
    TextInputEditText nombresEdit,Usuario_edit,Contrasena_edit,Celular_edit;
    FirebaseAuth auth;
    DatabaseReference database;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_infousuario, container, false);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        fullNombre = view.findViewById(R.id.full_name);
        fullcorreo = view.findViewById(R.id.labelCorreo);
        nombresEdit = view.findViewById(R.id.editNombres);
        Usuario_edit =  view.findViewById(R.id.editUsuario);
        Contrasena_edit = view.findViewById(R.id.editContrasna);
        Celular_edit = view.findViewById(R.id.editCelular);
        obtenerInfoUsuario();
        return view;
    }

    private void obtenerInfoUsuario(){
        String id =auth.getCurrentUser().getUid();
        database.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nombres = dataSnapshot.child("nombres").getValue().toString();
                String correo = dataSnapshot.child("email").getValue().toString();
                String usuario = dataSnapshot.child("usuario").getValue().toString();
                String contra = dataSnapshot.child("contrasena").getValue().toString();
                String celular = dataSnapshot.child("celular").getValue().toString();

                fullNombre.setText(nombres);
                fullcorreo.setText(correo);
                nombresEdit.getText().append(nombres).toString();
                Usuario_edit.getText().append(usuario).toString();
                Contrasena_edit.getText().append(contra).toString();
                Celular_edit.getText().append(celular).toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
