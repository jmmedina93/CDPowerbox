package com.example.jmmed.cdpowerbox.Objetos;


public class Reserva {
    private String hora;
    private String email;

    public Reserva(String hora, String email) {
        this.hora = hora;
        this.email = email;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
