/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import modelo.entidades.Tag;

/**
 *
 * @author Portege Z930
 */
@ApplicationScoped
public class TagDAO {
    
    @PersistenceContext(unitName = "GestorLibrosReseniasPU")
    private EntityManager em;
    
    public List<Tag> findAll(){
        return em.createQuery(
        "SELECT t FROM Tag t ORDER BY t.nomtag",
                Tag.class).getResultList();
    }
    
    public Tag findById(int id) {
        return em.find(Tag.class, id);
    }
    
}
