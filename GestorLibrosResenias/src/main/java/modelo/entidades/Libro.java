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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author MULTI
 */
@Entity
@Table(name = "tblibro")
public class Libro implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlibro")
    private int idlibro;
    
    @Column(name = "nombrelibro", nullable = false)  // NOT NULL en BD
    private String nombrelibro;
    
    @Column(name = "autor", nullable = false)        // NOT NULL en BD
    private String autor;
    
    @Column(name = "aniolibro")
    private Integer aniolibro;
    
    @Column(name = "isbn")
    private String isbn;
    
    @Column(name = "tematica", nullable = false)     // ¡NOT NULL en BD!
    private String tematica;
    
    @Column(name = "descripcion", nullable = false)  // ¡NOT NULL en BD!
    private String descripcion;
    
    @Column(name = "imglibro")
    private String imglibro;
    
    @OneToMany(mappedBy = "libro")
    private List<Resenia> resenias;
    
    @ManyToMany
    @JoinTable(
    name = "tbtaglibro",
    joinColumns = @JoinColumn(name = "idlibro"),
    inverseJoinColumns = @JoinColumn(name = "idtag"))
    private List<Tag> tags;

    public Libro() {}

    public Libro(int idlibro, String nombrelibro, String autor,
                 Integer aniolibro, String isbn, String tematica,
                 String descripcion, String imglibro) {

        this.idlibro = idlibro;
        this.nombrelibro = nombrelibro;
        this.autor = autor;
        this.aniolibro = aniolibro;
        this.isbn = isbn;
        this.tematica = tematica;
        this.descripcion = descripcion;
        this.imglibro = imglibro;
    }

    public int getIdlibro() { return idlibro; }
    public void setIdlibro(int idlibro) { this.idlibro = idlibro; }

    public String getNombrelibro() { return nombrelibro; }
    public void setNombrelibro(String nombrelibro) { this.nombrelibro = nombrelibro; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public Integer getAniolibro() { return aniolibro; }
    public void setAniolibro(Integer aniolibro) { this.aniolibro = aniolibro; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTematica() { return tematica; }
    public void setTematica(String tematica) { this.tematica = tematica; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImglibro() { return imglibro; }
    public void setImglibro(String imglibro) { this.imglibro = imglibro; }
    
    public List<Tag> getTags() { return tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }

    public List<Resenia> getResenias() { return resenias; }
    public void setResenias(List<Resenia> resenias) { this.resenias = resenias; }


    @Override
    public String toString() {
        return nombrelibro + " (" + autor + ")";
    }
}
