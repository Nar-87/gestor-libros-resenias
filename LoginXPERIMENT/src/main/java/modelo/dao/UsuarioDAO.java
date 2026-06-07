/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;
import modelo.entity.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author MULTI
 */

@Stateless
public class UsuarioDAO {

    @PersistenceContext(unitName = "LoginXPERIMENTPU")
    private EntityManager em;

    public Usuario login(String codusu, String passusu) {
        try {
            return em.createQuery(
                "SELECT u FROM Usuario u " +
                "WHERE u.codusu = :cod " +
                "AND u.passusu = :pass " +
                "AND u.baja = false",
                Usuario.class
            )
            .setParameter("cod", codusu)
            .setParameter("pass", passusu)
            .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }
}

