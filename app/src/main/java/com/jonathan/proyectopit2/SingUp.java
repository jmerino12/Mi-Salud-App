package com.jonathan.proyectopit2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jonathan.proyectopit2.model.Usuarios;

import java.util.UUID;

public class SingUp extends AppCompatActivity {
    Button btnTengoCuenta, btnRegistro;
    LinearLayout linearLayout;
    FirebaseDatabase database;
    DatabaseReference reference;
    TextInputLayout regnombre, regusuario,regemail,regcelular,regcontrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sing_up);
       btnTengoCuenta = findViewById(R.id.btn_tengo_cuenta);
       btnRegistro = findViewById(R.id.btn_registro);
       regnombre = findViewById(R.id.reg_nombres);
       regusuario = findViewById(R.id.reg_usuario);
       regemail = findViewById(R.id.reg_email);
       regcelular = findViewById(R.id.reg_celular);
       regcontrasena = findViewById(R.id.reg_contrasena);
       linearLayout = findViewById(R.id.linearLayout);
       //iniciarlizarFirebase();

        btnTengoCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = regnombre.getEditText().getText().toString();
                String usuario = regusuario.getEditText().getText().toString();
                String email = regemail.getEditText().getText().toString();
                String celular = regcelular.getEditText().getText().toString();
                String contrasena = regcontrasena.getEditText().getText().toString();
                Usuarios usuarios = new Usuarios();
                usuarios.setUdid(UUID.randomUUID().toString());
                usuarios.setNombres(nombre);
                usuarios.setUsuario(usuario);
                usuarios.setEmail(email);
                usuarios.setCeular(celular);
                usuarios.setContrasena(contrasena);
                database = FirebaseDatabase.getInstance();
                reference = database.getReference();
                reference.child("Persona").child(usuarios.getUdid()).setValue(usuarios);
                limpiarcajas();
                Snackbar snackbar = Snackbar
                        .make(linearLayout, "Registro Exitoso", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    private void limpiarcajas() {
        regnombre.getEditText().getText().clear();
        regusuario.getEditText().getText().clear();
        regemail.getEditText().getText().clear();
        regcelular.getEditText().getText().clear();
        regcontrasena.getEditText().getText().clear();
    }

    private void iniciarlizarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }
}
