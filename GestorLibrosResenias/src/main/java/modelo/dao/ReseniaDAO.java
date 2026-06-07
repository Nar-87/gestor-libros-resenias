/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import modelo.entidades.Resenia;

/**
 *
 * @author Portege Z930
 */
@ApplicationScoped
public class ReseniaDAO {

    @PersistenceContext(unitName = "GestorLibrosReseniasPU")
    private EntityManager em;

    public List<Resenia> findAll() {
        return em.createQuery(
                "SELECT r FROM Resenia r ORDER BY r.fechares DESC",
                Resenia.class
        ).getResultList();
    }

    public Resenia findById(int id) {
        return em.find(Resenia.class, id);
    }

    public List<Resenia> findByLibro(int idLibro) {
        return em.createQuery(
                "SELECT r FROM Resenia r WHERE r.libro.idlibro = :idLibro",
                Resenia.class
        )
        .setParameter("idLibro", idLibro)
        .getResultList();
    }

    public void insert(Resenia resenia) {
        em.persist(resenia);
    }

    public void update(Resenia resenia) {
        em.merge(resenia);
    }

    public void delete(int idResenia) {
        Resenia r = em.find(Resenia.class, idResenia);
        if (r != null) {
            em.remove(r);
        }
    }
}
