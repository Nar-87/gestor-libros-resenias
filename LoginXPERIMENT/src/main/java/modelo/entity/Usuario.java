/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author MULTI
 */
@Entity
@Table(name = "tbusuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusu")
    private Integer idusu;

    @Column(name = "codusu", nullable = false, length = 20)
    private String codusu;

    @Column(name = "nombcusu", nullable = false, length = 30)
    private String nombcusu;

    @Column(name = "emailusu", nullable = false, length = 30)
    private String emailusu;

    @Column(name = "rrssusu", length = 30)
    private String rrssusu;

    @Column(name = "passusu", nullable = false, length = 15)
    private String passusu;

    @Column(name = "baja", nullable = false)
    private boolean baja;

    @ManyToOne
    @JoinColumn(name = "idperfil", nullable = false)
    private Perfil perfil;
    
    public Usuario() {
    }

    public Usuario(int idusu, String codusu, String nombcusu,
                   String emailusu, String rrssusu, String passusu,
                   boolean baja, Perfil perfil) {

        this.idusu = idusu;
        this.codusu = codusu;
        this.nombcusu = nombcusu;
        this.emailusu = emailusu;
        this.rrssusu = rrssusu;
        this.passusu = passusu;
        this.baja = baja;
        this.perfil = perfil;
    }

    public int getIdusu() { return idusu; }
    public void setIdusu(int idusu) { this.idusu = idusu; }

    public String getCodusu() { return codusu; }
    public void setCodusu(String codusu) { this.codusu = codusu; }

    public String getNombcusu() { return nombcusu; }
    public void setNombcusu(String nombcusu) { this.nombcusu = nombcusu; }

    public String getEmailusu() { return emailusu; }
    public void setEmailusu(String emailusu) { this.emailusu = emailusu; }

    public String getRrssusu() { return rrssusu; }
    public void setRrssusu(String rrssusu) { this.rrssusu = rrssusu; }

    public String getPassusu() { return passusu; }
    public void setPassusu(String passusu) { this.passusu = passusu; }

    public boolean isBaja() { return baja; }
    public void setBaja(boolean baja) { this.baja = baja; }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }
}
