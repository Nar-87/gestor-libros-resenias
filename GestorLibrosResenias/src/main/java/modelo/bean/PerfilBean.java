/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package modelo.bean;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import modelo.entidades.Perfil;
import service.PerfilService;

/**
 *
 * @author Portege Z930
 */
@Named(value = "perfilBean")
@ViewScoped
public class PerfilBean implements Serializable {
    
    @Inject
    private PerfilService perfilService;

    private List<Perfil> listaPerfiles;
    /**
     * Creates a new instance of PerfilBean
     */
    public PerfilBean() {
    }
    
    @PostConstruct
    public void init() {
        listaPerfiles = perfilService.obtenerTodos();
    }

    public List<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }
    
}
