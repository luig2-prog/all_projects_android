package com.example.labparcial01;

import java.io.Serializable;

public class Canciones implements Serializable {

    private String nombre;
    private String artista;
    private String caracteristicas;
    private int año;
    private int posicion;
    private int imagenCantante;

    public Canciones(){

    }

    public Canciones(String nombre, String artista, String caracteristicas, int año, int posicion, int imagenCantante) {
        this.nombre = nombre;
        this.artista = artista;
        this.caracteristicas = caracteristicas;
        this.año = año;
        this.posicion = posicion;
        this.imagenCantante = imagenCantante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getImagenCantante() {
        return imagenCantante;
    }

    public void setImagenCantante(int imagenCantante) {
        this.imagenCantante = imagenCantante;
    }
}
