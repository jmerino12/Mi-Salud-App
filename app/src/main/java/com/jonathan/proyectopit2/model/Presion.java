package com.jonathan.proyectopit2.model;

public class Presion {
    private String udid;
    private String fecha;
    private String hora;
    private String presionDiastolica;
    private String presionSitolica;
    private String adicionales;
    private String peso;

    public Presion() {
    }


    public Presion(String fecha, String hora, String presionDiastolica, String presionSitolica, String adicionales, String peso) {
        this.fecha = fecha;
        this.hora = hora;
        this.presionDiastolica = presionDiastolica;
        this.presionSitolica = presionSitolica;
        this.adicionales = adicionales;
        this.peso = peso;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPresionDiastolica() {
        return presionDiastolica;
    }

    public void setPresionDiastolica(String presionDiastolica) {
        this.presionDiastolica = presionDiastolica;
    }

    public String getPresionSitolica() {
        return presionSitolica;
    }

    public void setPresionSitolica(String presionSitolica) {
        this.presionSitolica = presionSitolica;
    }

    public String getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(String adicionales) {
        this.adicionales = adicionales;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}

