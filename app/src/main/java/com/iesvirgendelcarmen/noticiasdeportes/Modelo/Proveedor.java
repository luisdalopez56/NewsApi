package com.iesvirgendelcarmen.noticiasdeportes.Modelo;

public class Proveedor {

    private String nombre;
    private int imagen;
    private String keyUrl;

    public Proveedor(String nombre, int imagen, String keyUrl) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.keyUrl = keyUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getKeyUrl() {
        return keyUrl;
    }

    public void setKeyUrl(String keyUrl) {
        this.keyUrl = keyUrl;
    }

    @Override
    public String toString() {
        return getKeyUrl();
    }
}
