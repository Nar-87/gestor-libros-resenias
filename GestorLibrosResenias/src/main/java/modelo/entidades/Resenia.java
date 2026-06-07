/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Portege Z930
 */
@Entity
@Table(name = "tbresenia")
public class Resenia implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idresenia")
    private Integer idresenia;

    @Column(name = "titulores", length = 50)
    private String titulores;

    @Column(name = "puntosres", nullable = false)
    private int puntosres;

    @Column(name = "contenidores")
    private String contenidores;

    @Column(name = "fechares", nullable = false)
    private Date fechares;

    @Column(name = "idusu", nullable = false)
    private Integer idusu;
    
    @ManyToOne
    @JoinColumn(name = "idlibro", nullable = false)
    private Libro libro;
    

    public Resenia() {}

    public Resenia(int idresenia, String titulores, int puntosres,
                   String contenidores, Date fechares,
                   Libro libro, Integer idusu) {

        this.idresenia = idresenia;
        this.titulores = titulores;
        this.puntosres = puntosres;
        this.contenidores = contenidores;
        this.fechares = fechares;
        this.libro = libro;
        this.idusu = idusu;
    }

    public Integer getIdresenia() {
        return idresenia;
    }

    public void setIdresenia(Integer idresenia) {
        this.idresenia = idresenia;
    }

    public String getTitulores() {
        return titulores;
    }

    public void setTitulores(String titulores) {
        this.titulores = titulores;
    }

    public int getPuntosres() {
        return puntosres;
    }

    public void setPuntosres(int puntosres) {
        this.puntosres = puntosres;
    }

    public String getContenidores() {
        return contenidores;
    }

    public void setContenidores(String contenidores) {
        this.contenidores = contenidores;
    }

    public Date getFechares() {
        return fechares;
    }

    public void setFechares(Date fechares) {
        this.fechares = fechares;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Integer getIdusu() {
        return idusu;
    }

    public void setIdusu(Integer idusu) {
        this.idusu = idusu;
    }
        
}
