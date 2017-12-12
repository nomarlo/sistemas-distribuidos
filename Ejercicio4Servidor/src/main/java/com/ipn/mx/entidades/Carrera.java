package com.ipn.mx.entidades;

import java.io.Serializable;

public class Carrera implements Serializable{


    private int idCarrera;
    private String nombreCarrera;

    public Carrera() {
    }


    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }
}
