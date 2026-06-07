package modelo.bean;

import service.LibroService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import modelo.entidades.Libro;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named(value = "libroBean")
@ViewScoped
public class LibroBean implements Serializable {

    @Inject
    private LibroService libroService;

    private Libro libroSeleccionado;
    private List<Libro> listaLibros;
    private Libro libro;
    private boolean editando;

    //variable para que no se me borre la imagen del libro desde la bd cuando edito
    private String imglibroOriginal;

    @PostConstruct
    public void init() {
        libro = new Libro();
        editando = false;
        cargarLibros();
    }
    
    // Getters y Setters
    public LibroService getLibroService() {
        return libroService;
    }

    public void setLibroService(LibroService libroService) {
        this.libroService = libroService;
    }

    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }
    
    public Libro getLibroSeleccionado() {
        return libroSeleccionado;
    }

    public void setLibroSeleccionado(Libro libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }

    //métodos de la clase
    public void cargarLibros() {
        listaLibros = libroService.cargarLibros();
    }

    public void nuevoLibro() {
        libro = new Libro();
        editando = false;
    }

    public void editarLibro(Libro l) {
        libro = l;
        imglibroOriginal = l.getImglibro();
        editando = true;
    }

    public void guardarLibro() {
        try {
            // Validación básica
            if (libro.getNombrelibro() == null || libro.getNombrelibro().trim().isEmpty()) {
                throw new IllegalArgumentException("El título es obligatorio");
            }
            if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()) {
                throw new IllegalArgumentException("El autor es obligatorio");
            }
            if (libro.getTematica() == null || libro.getTematica().trim().isEmpty()) {
                throw new IllegalArgumentException("La editorial es obligatoria");
            }
            if (libro.getDescripcion() == null || libro.getDescripcion().trim().isEmpty()) {
                throw new IllegalArgumentException("La descripción es obligatoria");
            }

            // Limpiar espacios
            libro.setNombrelibro(libro.getNombrelibro().trim());
            if (editando && libro.getImglibro() == null) {
                libro.setImglibro(imglibroOriginal);
            }
            libro.setAutor(libro.getAutor().trim());
            libro.setTematica(libro.getTematica().trim());
            libro.setDescripcion(libro.getDescripcion().trim());

            if (editando) {
                libroService.actualizarLibro(libro);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Éxito", "Libro actualizado correctamente"));
            } else {
                libroService.crearLibro(libro);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Éxito", "Libro añadido correctamente"));
            }

            // Limpiar y recargar
            nuevoLibro();
            cargarLibros();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", e.getMessage() != null ? e.getMessage() : "Error al guardar"));
        }
    }

    public void eliminarLibro(Libro l) {
        try {
            libroService.eliminarLibro(l.getIdlibro());
            cargarLibros();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Libro eliminado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error al eliminar", e.getMessage()));
        }
    }

    public void eliminarLibroSeleccionado() {
        if (libroSeleccionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Aviso", "Selecciona un libro primero"));
            return;
        }

        libroService.eliminarLibro(libroSeleccionado.getIdlibro());
        cargarLibros();
        libroSeleccionado = null;
    }

    //método para exportar todos los libros de la base de datos 
    public StreamedContent getLibrosCSV() {
        List<Libro> libros = obtenerListaLibros();

        StringBuilder csv = new StringBuilder();
        csv.append("ID;Título;Autor;Editorial;Año;ISBN;Descripción\n");

        for (Libro libro : libros) {
            csv.append(libro.getIdlibro()).append(";")
                    .append(escapeCSV(libro.getNombrelibro())).append(";")
                    .append(escapeCSV(libro.getAutor())).append(";")
                    .append(escapeCSV(libro.getTematica())).append(";")
                    .append(libro.getAniolibro() != null ? libro.getAniolibro() : "").append(";")
                    .append(escapeCSV(libro.getIsbn())).append(";")
                    .append(escapeCSV(libro.getDescripcion()))
                    .append("\n");
        }

        InputStream stream = new ByteArrayInputStream(csv.toString().getBytes(StandardCharsets.UTF_8));

        return DefaultStreamedContent.builder()
                .name("libros_exportados.csv")
                .contentType("text/csv")
                .stream(() -> stream)
                .build();
    }

    //método para evitar que el CSV se rompa cuando los datos contienen caracteres especiales
    private String escapeCSV(String input) {
        if (input == null) {
            return "";
        }
        // evitar escapar comillas y punto y coma
        return "\"" + input.replace("\"", "\"\"") + "\"";
    }

    public List<Libro> obtenerListaLibros() {
        // Verificar si está la lista cargada
        if (listaLibros == null) {
            // Cargar desde el servicio
            listaLibros = libroService.cargarLibros();

            if (listaLibros.isEmpty()) {
                FacesMessage message = new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Sin libros",
                        "No hay libros registrados en el sistema"
                );
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
        return listaLibros;
    }

}
