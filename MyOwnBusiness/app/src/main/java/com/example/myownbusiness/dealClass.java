package com.example.myownbusiness;

import java.io.Serializable;

public class dealClass implements Serializable {
    //Contants
    String codigo_servicio;
    String codigo_usuario;
    String nombre;
    String categoria1;
    String categoria2;
    String categoria3;
    String localizacion;
    String precio;

    //Contuctor with al vars.
    public dealClass(String codigo_servicio, String codigo_usuario, String nombre, String categoria1,
                          String categoria2, String categoria3, String localizacion, String precio, String descripcion, String rango) {
        this.codigo_servicio = codigo_servicio;
        this.codigo_usuario = codigo_usuario;
        this.nombre = nombre;
        this.categoria1 = categoria1;
        this.categoria2 = categoria2;
        this.categoria3 = categoria3;
        this.localizacion = localizacion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.rango = rango;
    }

    //Void contructor.
    public dealClass() {
    }

    //GETTERS-------------------------------------------------------------------------------------->
    public String getCodigo_servicio() {
        return codigo_servicio;
    }

    public void setCodigo_servicio(String codigo_servicio) {
        this.codigo_servicio = codigo_servicio;
    }

    public String getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(String codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria1() {
        return categoria1;
    }

    public void setCategoria1(String categoria1) {
        this.categoria1 = categoria1;
    }

    public String getCategoria2() {
        return categoria2;
    }

    public void setCategoria2(String categoria2) {
        this.categoria2 = categoria2;
    }

    public String getCategoria3() {
        return categoria3;
    }

    public void setCategoria3(String categoria3) {
        this.categoria3 = categoria3;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    //SETTERS-------------------------------------------------------------------------------------->

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    String descripcion;
    String rango;

}
