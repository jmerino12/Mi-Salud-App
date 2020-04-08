package com.jonathan.proyectopit2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    TextInputLayout emailsingin,password;
    FirebaseAuth auth;
    LinearLayout loginLayout;



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
        emailsingin = findViewById(R.id.usuario);
        password = findViewById(R.id.password);
        loginLayout = findViewById(R.id.layout_login);
        login_btn = findViewById(R.id.btn_entrar);
        auth = FirebaseAuth.getInstance();

        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SingUp.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(logoText,"logo_text");
                pairs[2] = new Pair<View,String>(sloganText,"logo_desc");
                pairs[3] = new Pair<View,String>(emailsingin,"username_tran");
                pairs[4] = new Pair<View,String>(password,"password_tran");
                pairs[5] = new Pair<View,String>(login_btn,"button_tran");
                pairs[6] = new Pair<View,String>(registrarme,"login_singup_tran");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });
    }

    public void loginUsuario(View view) {
        if (!validarEmail() | !validarContrasena()){
            return;
        }else{
            isUser();
        }
    }

    private void isUser() {
        final String email = emailsingin.getEditText().getText().toString();
        final String contrasena = password.getEditText().getText().toString();
        auth.signInWithEmailAndPassword(email,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    System.out.println(user);
                    startActivity(new Intent(Login.this,Dashboard.class));
                    finish();
                }else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Login.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
                    Snackbar snackbar = Snackbar
                            .make(loginLayout, "No se pudo iniciar sesión, Compruebe los datos", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
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

    private boolean validarEmail() {
        String val = emailsingin.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            emailsingin.setError("El campo no puede estar vacío.");
            return false;
        } else if (!val.matches(emailPattern)) {
            emailsingin.setError("Dirección de correo electrónico no válida");
            return false;
        } else {
            emailsingin.setError(null);
            emailsingin.setErrorEnabled(false);
            return true;
        }
    }
}
