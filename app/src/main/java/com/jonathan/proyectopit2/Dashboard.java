package com.jonathan.proyectopit2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.jonathan.proyectopit2.tabs.PresionFragment;

public class Dashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new DatosFragment()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener  = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragmentSeleccionado = null;
            switch (item.getItemId()){
                case R.id.nav_datos:
                    fragmentSeleccionado = new DatosFragment();
                    break;
                case R.id.nav_historia:
                    fragmentSeleccionado = new RegistroFragment();
                    break;
                case R.id.nav_informacionUsuario:
                    fragmentSeleccionado = new InfoUsuariosFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentSeleccionado).commit();
            return true;
        }
    };

}
