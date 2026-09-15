package com.example;

public class Cliente {
    private String nombre;
    private String telefono;
    private String correo;

    public Cliente(String nombre, String telefono, String correo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío");
        }
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }
}
