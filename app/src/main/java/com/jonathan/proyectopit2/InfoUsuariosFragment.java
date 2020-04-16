package com.jonathan.proyectopit2;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class InfoUsuariosFragment extends Fragment {
    private TextView fullNombre, fullcorreo;
    private TextInputEditText nombresEdit,Usuario_edit,Contrasena_edit,Celular_edit;
    private TextInputLayout nombres, usuarios, contrasena, celular;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private Button btnActualizar;
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

        nombres = view.findViewById(R.id.txtNombres);
        usuarios = view.findViewById(R.id.txt_usuario);
        contrasena = view.findViewById(R.id.txt_contrasena);
        celular = view.findViewById(R.id.txt_celular);
        btnActualizar = view.findViewById(R.id.btnActualizar);
        obtenerInfoUsuario();
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validarNombre() | !validarUsuario()  | !validarCelular() | !validarContrasena()){
                    return;
                }
                String id =auth.getCurrentUser().getUid();
                final String nombre = nombres.getEditText().getText().toString();
                final String usuario = usuarios.getEditText().getText().toString();
                final String celularm = celular.getEditText().getText().toString();
                final String contrasenas = contrasena.getEditText().getText().toString();
                final String email = fullcorreo.getText().toString();
                Map<String, Object> map = new HashMap<>();
                map.put("nombres", nombre);
                map.put("usuario", usuario);
                map.put("celular",celularm);
                map.put("email",email);
                map.put("contrasena",contrasenas);
                database.child("Usuarios").child(id).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(),"Registro Actualizado", Toast.LENGTH_LONG).show();
                        limpiarcajas();
                        obtenerInfoUsuario();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Hubo un error" + e, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
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
    public void limpiarcajas(){
        nombresEdit.getText().clear();
        Usuario_edit.getText().clear();
        Contrasena_edit.getText().clear();
        Celular_edit.getText().clear();
    }
    private Boolean validarNombre(){
        String val = nombres.getEditText().getText().toString();
        if (val.isEmpty()) {
            nombres.setError("El campo no puede estar vacío.");
            return false;
        }
        else {
            nombres.setError(null);
            nombres.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validarUsuario(){
        String val = usuarios.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            usuarios.setError("El campo no puede estar vacío.");
            return false;
        } else if (val.length() >= 15) {
            usuarios.setError("Nombre de usuario demasiado largo");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            usuarios.setError("No se permiten espacios en blanco");
            return false;
        } else {
            usuarios.setError(null);
            usuarios.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validarCelular(){
        String val = celular.getEditText().getText().toString();

        if (val.isEmpty()) {
            celular.setError("El campo no puede estar vacío.");
            return false;
        } else {
            celular.setError(null);
            celular.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validarContrasena(){
        String val = contrasena.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            contrasena.setError("El campo no puede estar vacío.");
            return false;
        } else if (!val.matches(passwordVal)) {
            contrasena.setError("La contraseña es demasiado débil");
            return false;
        } else {
            contrasena.setError(null);
            contrasena.setErrorEnabled(false);
            return true;
        }
    }

}
