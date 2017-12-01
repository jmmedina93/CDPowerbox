package com.example.jmmed.cdpowerbox.Objetos;

/**
 * Created by jmmed on 01/12/2017.
 */

public class Usuario {
    String email;
    String nombre;
    String apellidos;
    String telefono;

    public Usuario() {
    }

    public Usuario(String email, String nombre, String apellidos, String telefono) {
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
