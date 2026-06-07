/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import modelo.dao.PerfilDAO;
import modelo.entidades.Perfil;

/**
 *
 * @author Portege Z930
 */
@ApplicationScoped
public class PerfilService {
    
    @Inject
    private PerfilDAO perfilDAO;
    
    public List<Perfil> obtenerTodos() {
        return perfilDAO.findAll();
    }
    
    public Perfil buscarPorId(int id) {
        return perfilDAO.findById(id);
    }
    
}
