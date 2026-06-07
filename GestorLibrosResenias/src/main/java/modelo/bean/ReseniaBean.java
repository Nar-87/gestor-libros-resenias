/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package modelo.bean;

import service.ReseniaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.entidades.Libro;
import modelo.entidades.Resenia;
import service.LibroService;

/**
 *
 * @author Portege Z930
 */
@Named(value = "reseniaBean")
@ViewScoped
public class ReseniaBean implements Serializable {

    @Inject
    private ReseniaService reseniaService;

    @Inject
    private LoginBean loginBean;

    @Inject
    private LibroService libroService;

    @Inject
    private LibroBean libroBean;

    private Resenia resenia;
    private List<Resenia> listaResenias;

    private Libro libroSeleccionado;

    private List<Resenia> listaReseniasPorLibro;
    private List<Libro> listaLibros;
    private boolean mostrarFormulario = false;

    private Integer libroId;

    @PostConstruct
    public void init() {
           //para leer la url anterior, en vez de usar JS
          // Inicializa datos; Lee parámetros (flash); Prepara la vista
        Map<String, Object> flash
                = FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getFlash();

        Object libroIdObj = flash.get("libroId");
        if (libroIdObj != null) {
            int idLibro = (int) libroIdObj;

            libroSeleccionado = libroService.buscarPorId(idLibro);
            listaReseniasPorLibro = reseniaService.obtenerPorLibro(idLibro);
        }

        resenia = new Resenia();
        cargarLibros();
          
    }
    
    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public List<Resenia> getListaReseniasPorLibro() {
        return listaReseniasPorLibro;
    }

    public void setListaReseniasPorLibro(List<Resenia> listaReseniasPorLibro) {
        this.listaReseniasPorLibro = listaReseniasPorLibro;
    }

    public boolean isMostrarFormulario() {
        return mostrarFormulario;
    }

    public void setMostrarFormulario(boolean mostrarFormulario) {
        this.mostrarFormulario = mostrarFormulario;
    }

    public ReseniaService getReseniaService() {
        return reseniaService;
    }

    public void setReseniaService(ReseniaService reseniaService) {
        this.reseniaService = reseniaService;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Resenia getResenia() {
        return resenia;
    }

    public void setResenia(Resenia resenia) {
        this.resenia = resenia;
    }

    public List getListaResenias() {
        return listaResenias;
    }

    public void setListaResenias(List listaResenias) {
        this.listaResenias = listaResenias;
    }

    public Libro getLibroSeleccionado() {
        return libroSeleccionado;
    }

    public void setLibroSeleccionado(Libro libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }

    public Integer getLibroId() {
        return libroId;
    }

    public void setLibroId(Integer libroId) {
        this.libroId = libroId;
    }
    
    public void cargarLibros() {
        listaLibros = libroService.cargarLibros();
    }

    public void cargarResenias() {
        listaResenias = reseniaService.obtenerTodas();
    }

    public void nuevaResenia(Libro libro) {
        this.resenia = new Resenia();
        this.libroSeleccionado = libro;
        this.mostrarFormulario = true;
    }

    public void verResenias(Libro libro) {
        this.libroSeleccionado = libro;
        this.listaReseniasPorLibro = reseniaService.obtenerPorLibro(libro.getIdlibro());
        this.mostrarFormulario = false;
    }

    public void guardarResenia() {
        try {
            // Debe haber un libro seleccionado
            if (libroSeleccionado == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "No hay ningún libro seleccionado"));
                return;
            }

            // Usuario debe estar logueado
            if (loginBean == null || loginBean.getIdusu() == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "Debes iniciar sesión para crear reseñas"));
                return;
            }

            if (resenia.getTitulores() == null || resenia.getTitulores().trim().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "El título de la reseña es obligatorio"));
                return;
            }

            if (resenia.getTitulores().trim().length() < 3) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "El título debe tener al menos 3 caracteres"));
                return;
            }

            if (resenia.getPuntosres() < 1 || resenia.getPuntosres() > 10) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "La puntuación debe estar entre 1 y 10"));
                return;
            }

            if (resenia.getContenidores() == null || resenia.getContenidores().trim().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "El contenido de la reseña es obligatorio"));
                return;
            }

            if (resenia.getContenidores().trim().length() < 10) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "El contenido debe tener al menos 10 caracteres"));
                return;
            }

            // Que el usuario no haya reseñado ya este libro
            List<Resenia> reseniasDelLibro = reseniaService.obtenerPorLibro(libroSeleccionado.getIdlibro());
            for (Resenia r : reseniasDelLibro) {
                if (r.getIdusu().equals(loginBean.getIdusu())) {
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "Ya has reseñado este libro. Solo puedes hacer una reseña por libro."));
                    return;
                }
            }

            resenia.setLibro(libroSeleccionado);
            resenia.setFechares(new java.sql.Date(System.currentTimeMillis()));
            resenia.setIdusu(loginBean.getIdusu());

            reseniaService.crearResenia(resenia);

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito",
                    "Reseña guardada correctamente"));

            // Recargar reseñas del libro seleccionado
            listaReseniasPorLibro = reseniaService.obtenerPorLibro(libroSeleccionado.getIdlibro());
            // Recargar datos
            libroBean.cargarLibros();
            cargarLibros();
            // Ocultar formulario y resetear
            mostrarFormulario = false;
            resenia = new Resenia();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Error al guardar la reseña: " + e.getMessage()));
        }
    }

    public int contarResenias(int idLibro) {
        return reseniaService.obtenerPorLibro(idLibro).size();
    }

    public void cancelarResenia() {
        this.resenia = new Resenia();
        this.mostrarFormulario = false;
    }

    public List<Integer> getPuntuacionesDisponibles() {
        List<Integer> puntuaciones = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            puntuaciones.add(i);
        }
        return puntuaciones;
    }

    //para redirigir desde la página de Sugerencias a la de Reseñas
    public String verReseniasYRedirigir(Libro libro) {

        this.libroSeleccionado = libro;
        this.listaReseniasPorLibro
                = reseniaService.obtenerPorLibro(libro.getIdlibro());

        this.mostrarFormulario = false;

        // Pasamos el ID por flash scope, permite pasar parámetros o atributos entre dos vistas distintas
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash()
                .put("libroId", libro.getIdlibro());

        return "/resenias?faces-redirect=true";
    }

    //es del primer botón del xhtml de Ayuda para volver a la página anterior
    public String volverDesdeAyuda() {
        return "/resenias?faces-redirect=true";
    }
}
