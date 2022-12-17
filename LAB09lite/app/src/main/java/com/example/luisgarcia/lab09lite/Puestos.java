package com.example.luisgarcia.lab09lite;

public class Puestos {

    private String nombre;
    private String apellido;

    public Puestos() {
    }

    public Puestos(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
