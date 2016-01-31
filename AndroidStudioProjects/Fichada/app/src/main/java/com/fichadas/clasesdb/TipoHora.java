package com.fichadas.clasesdb;

/**
 * Created by informatica on 26/01/16.
 */
public class TipoHora {
     private int   codigo;
    private String descripcion;
    private double precio;
    private int    extra;

    public void TipoHora(){}
    public void TipoHora(int xcod, String des, double preu, int extr)
    {
        codigo = xcod;
        descripcion = des;
        precio = preu;
        extra = extr;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExtra() {
        return extra;
    }

    public void setExtra(int extra) {
        this.extra = extra;
    }
}
