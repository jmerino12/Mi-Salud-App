package com.jonathan.proyectopit2.model;

public class Usuarios {
    private String udid;
    private String Nombres;
    private String usuario;
    private String email;
    private String ceular;
    private String contrasena;

    public Usuarios() {
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCeular() {
        return ceular;
    }

    public void setCeular(String ceular) {
        this.ceular = ceular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
