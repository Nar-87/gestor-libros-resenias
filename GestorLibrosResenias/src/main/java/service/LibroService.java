package service;

import jakarta.inject.Inject;
import java.util.List;
import modelo.dao.LibroDAO;
import modelo.entidades.Libro;

import jakarta.ejb.Stateless;

@Stateless
public class LibroService {

    @Inject
    private LibroDAO libroDAO;

    public List<Libro> cargarLibros() {
        return libroDAO.findAll();
    }

    public void crearLibro(Libro libro) {
        libroDAO.insert(libro);
    }

    public Libro buscarPorId(int id) {
        return libroDAO.findById(id);
    }

    public void eliminarLibro(int idLibro) {
        libroDAO.delete(idLibro);
    }
    
    public void actualizarLibro(Libro libro) {
        libroDAO.update(libro);
    }
    
    public List<Libro> buscarPorAutor(String autor) {
        return libroDAO.findByAutor(autor);
    }

    public List<Libro> buscarLibrosPorUsuariosConBuenasResenias(int idLibro) {
        return libroDAO.findLibrosPorUsuariosConBuenasResenias(idLibro);
    }
    
    public List<Libro> buscarLibrosPorReseniasSimilares(int idLibroBase) {
        return libroDAO.findLibrosPorReseniasSimilares(idLibroBase);
    }
}
