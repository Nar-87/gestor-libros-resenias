/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import modelo.entidades.Perfil;

/**
 *
 * @author Portege Z930
 */
@ApplicationScoped
public class PerfilDAO {
    
    @PersistenceContext(unitName = "GestorLibrosReseniasPU")
    private EntityManager em;

    public List<Perfil> findAll() {
        return em.createQuery(
        "SELECT p FROM Perfil p ORDER BY p.idperfil",
                Perfil.class).getResultList();
    }    
    
    public Perfil findById(int id) {
        return em.find(Perfil.class, id);
    }
}
