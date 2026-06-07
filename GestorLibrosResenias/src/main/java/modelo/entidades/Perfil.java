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
import java.io.Serializable;

/**
 *
 * @author Portege Z930
 */
@Entity
@Table(name = "tbperfil")
public class Perfil implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idperfil")
    private int idperfil;
    
    @Column(name = "desperfil")
    private String desperfil;
    
    public Perfil() {}
    
    public Perfil(int idperfil, String desperfil) {
        this.idperfil = idperfil;
        this.desperfil = desperfil;
    }

    public int getIdperfil() { return idperfil; }
    public void setIdperfil(int idperfil) { this.idperfil = idperfil; }

    public String getDesperfil() { return desperfil; }
    public void setDesperfil(String desperfil) { this.desperfil = desperfil; }

    @Override
    public String toString() {
        return desperfil;
    }
}
