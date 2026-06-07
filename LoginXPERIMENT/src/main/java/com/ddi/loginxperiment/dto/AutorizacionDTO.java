package com.ddi.loginxperiment.dto;

public class AutorizacionDTO {
    
    private boolean logeado;
    private Integer idusu;
    private String nombre;
    private String rol;

    public AutorizacionDTO() {}

    public AutorizacionDTO(boolean logeado, Integer idusu, String nombre, String rol) {
        this.logeado = logeado;
        this.idusu = idusu;
        this.nombre = nombre;
        this.rol = rol;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public Integer getIdusu() {
        return idusu;
    }

    public void setIdusu(Integer idusu) {
        this.idusu = idusu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
   
 
}
