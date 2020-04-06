package com.jonathan.proyectopit2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jonathan.proyectopit2.model.Usuarios;

import java.sql.SQLOutput;
import java.util.UUID;

public class Login extends AppCompatActivity {
    Button registrarme,login_btn;
    ImageView image;
    TextView logoText,sloganText;
    TextInputLayout username,password;
    Usuarios usuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        //Vincular
        registrarme = findViewById(R.id.btn_registrarse);
        image = findViewById(R.id.logoLogin);
        logoText = findViewById(R.id.mensajeBinevenido);
        sloganText = findViewById(R.id.sloganmensaje);
        username = findViewById(R.id.usuario);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.btn_entrar);

        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SingUp.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(logoText,"logo_text");
                pairs[2] = new Pair<View,String>(sloganText,"logo_desc");
                pairs[3] = new Pair<View,String>(username,"username_tran");
                pairs[4] = new Pair<View,String>(password,"password_tran");
                pairs[5] = new Pair<View,String>(login_btn,"button_tran");
                pairs[6] = new Pair<View,String>(registrarme,"login_singup_tran");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });
    }

    public void loginUsuario(View view) {
        if (!validarUsuario() | !validarContrasena()){
            return;
        }else{
            isUser();
        }
    }

    private void isUser() {
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Usuarios");
        Query checkUser = reference.orderByChild("usuario").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("contrasena").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("nombres").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("usuario").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("celular").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        intent.putExtra("nombre", nameFromDB);
                        intent.putExtra("usuario", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("celular", phoneNoFromDB);
                        intent.putExtra("contrasena", passwordFromDB);
                        startActivity(intent);
                    } else {
                       // progressBar.setVisibility(View.GONE);
                        password.setError("Contraseña erronea");
                        password.requestFocus();
                    }
                } else {
                    //progressBar.setVisibility(View.GONE);
                    username.setError("El usuarios no existe");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean validarContrasena() {
        String val = password.getEditText().getText().toString();

        if (val.isEmpty()) {
            password.setError("El campo no puede estar vacío.");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validarUsuario() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("El campo no puede estar vacío.");
            return false;
        }  else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
}
