package modelo.dao;

import modelo.entidades.Libro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class LibroDAO {

    @PersistenceContext(unitName = "GestorLibrosReseniasPU")
    private EntityManager em;
    
    public List<Libro> findAll() {
        return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
    }

    public Libro findById(int id) {
        return em.find(Libro.class, id);
    }
    
    public List<Libro> findByAutor(String autor) {
        return em.createQuery(
            "SELECT l FROM Libro l WHERE l.autor = :autor",
            Libro.class)
            .setParameter("autor", autor)
            .getResultList();
    }
    
    public List<Libro> findLibrosPorUsuariosConBuenasResenias(int idLibro) {
        return em.createQuery(
                "SELECT DISTINCT r2.libro " +
                "FROM Resenia r1, Resenia r2 " +
                "WHERE r1.libro.idlibro = :idLibro " +
                "AND r1.puntosres >= 4 " +
                "AND r2.idusu = r1.idusu " +
                "AND r2.libro.idlibro <> :idLibro",
                Libro.class
            )
        .setParameter("idLibro", idLibro)
        .getResultList();
    }
    
    public List<Libro> findLibrosPorReseniasSimilares(int idLibroBase) {
        return em.createQuery(
            "SELECT DISTINCT r2.libro " +
            "FROM Resenia r1, Resenia r2 " +
            "WHERE r1.libro.idlibro = :libroBase " +
            "AND r1.idusu = r2.idusu " +
            "AND r2.puntosres >= 4 " +
            "AND r2.libro.idlibro <> :libroBase",
            Libro.class
        )
        .setParameter("libroBase", idLibroBase)
        .getResultList();
    }
    
    public List<Libro> buscarPorTituloOAutor(String termino) {
        return em.createQuery(
            "SELECT l FROM Libro l WHERE " +
            "LOWER(l.nombrelibro) LIKE :termino OR " +
            "LOWER(l.autor) LIKE :termino " +
            "ORDER BY l.nombrelibro", Libro.class)
            .setParameter("termino", "%" + termino + "%")
            .getResultList();
    }
     
    public void insert(Libro libro) {
        try {
            System.out.println("DAO.insert - Antes de persist: " + libro);
            em.persist(libro);
            em.flush(); // Forzar inserción para ver error
            System.out.println("DAO.insert - Libro persistido OK");
        } catch (Exception e) {
            System.out.println("DAO.insert - ERROR: " + e.getMessage());
            throw e;
        }
    }
    
    public void update(Libro libro) {
        em.merge(libro);
    }
    
    public void delete(int id) {
        Libro l = em.find(Libro.class, id);
        if (l != null) {
            em.remove(l);
        }
    }
}
