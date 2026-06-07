/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import modelo.dao.TagDAO;
import modelo.entidades.Tag;

/**
 *
 * @author Portege Z930
 */
@ApplicationScoped
public class TagService {
    
    @Inject
    private TagDAO tagDAO;
    
    public List<Tag> obtenerTodos() {
        return tagDAO.findAll();
    }
    
    public Tag buscarPorId(int id) {
        return tagDAO.findById(id);
    }
    
}
