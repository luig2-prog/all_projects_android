package com.example.imagenesasyntask;

import android.graphics.Bitmap;

public class CelularesModelo {
    private String nombreCel, Propiedades;
    Bitmap imgCelular;

    public CelularesModelo(String nombreCel, String propiedades) {
        this.nombreCel = nombreCel;
        Propiedades = propiedades;

    }

    public String getNombreCel() {
        return nombreCel;
    }

    public void setNombreCel(String nombreCel) {
        this.nombreCel = nombreCel;
    }

    public String getPropiedades() {
        return Propiedades;
    }

    public void setPropiedades(String propiedades) {
        Propiedades = propiedades;
    }



}
