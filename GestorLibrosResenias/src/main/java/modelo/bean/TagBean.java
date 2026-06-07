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
import modelo.entidades.Tag;
import service.TagService;

/**
 *
 * @author Portege Z930
 */
@Named(value = "tagBean")
@ViewScoped
public class TagBean implements Serializable {

    @Inject
    private TagService tagService;

    private List<Tag> listaTags;
    /**
     * Creates a new instance of TagBean
     */
    public TagBean() {
    }
    
    @PostConstruct
    public void init() {
        listaTags = tagService.obtenerTodos();
    }

    public List<Tag> getListaTags() {
        return listaTags;
    }

    public void setListaTags(List<Tag> listaTags) {
        this.listaTags = listaTags;
    }
    
}
