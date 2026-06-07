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
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Portege Z930
 */
@Entity
@Table(name = "tbtag")
public class Tag implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtag")
    private int idtag;
    
    @Column(name = "nomtag")
    private String nomtag;
    
    @ManyToMany(mappedBy = "tags")
    private List<Libro> libros;
    
    public Tag() {}

    public Tag(int idtag, String nomtag) {
        this.idtag = idtag;
        this.nomtag = nomtag;
    }

    public int getIdtag() { return idtag; }
    public void setIdtag(int idtag) { this.idtag = idtag; }

    public String getNomtag() { return nomtag; }
    public void setNomtag(String nomtag) { this.nomtag = nomtag; }
    
    public List<Libro> getLibros() { return libros; }

    public void setLibros(List<Libro> libros) { this.libros = libros; }

    @Override
    public String toString() {
        return nomtag;
    }
}
