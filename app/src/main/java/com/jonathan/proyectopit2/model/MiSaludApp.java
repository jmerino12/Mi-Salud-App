package com.jonathan.proyectopit2.model;

import com.google.firebase.database.FirebaseDatabase;

public class MiSaludApp extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
