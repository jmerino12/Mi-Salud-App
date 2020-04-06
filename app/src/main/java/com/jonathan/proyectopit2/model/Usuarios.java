package com.jonathan.proyectopit2.model;

public class Usuarios {
    private String udid;
    private String Nombres;
    private String usuario;
    private String email;
    private String celular;
    private String contrasena;

    public Usuarios() {
    }

    public Usuarios(String nombres, String usuario, String email, String celular, String contrasena, String udid) {
        Nombres = nombres;
        this.usuario = usuario;
        this.email = email;
        this.celular = celular;
        this.contrasena = contrasena;
        this.udid = udid;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


}
