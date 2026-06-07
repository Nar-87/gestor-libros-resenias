/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package modelo.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import modelo.entidades.Libro;
import service.LibroService;
import service.ReseniaService;

/**
 *
 * @author Portege Z930
 */
@Named(value = "sugerenciaBean")
@ViewScoped
public class SugerenciaBean implements Serializable {

   @Inject
    private LibroService libroService;

    @Inject
    private ReseniaService reseniaService;

    private String tipoSugerencia;
    private String autorSeleccionado;
    private Libro libroBase;
    private List<Libro> librosSugeridos;
    private String mensajeSugerencia;

    @PostConstruct
    public void init() {
        
        //  para leer la url anterior, en vez de usar JS 
        Map<String, String> params =
            FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequestParameterMap();

        tipoSugerencia = params.get("tipo");

        if ("autor".equals(tipoSugerencia)) {
            autorSeleccionado = params.get("autor");
            librosSugeridos = libroService.buscarPorAutor(autorSeleccionado);
            mensajeSugerencia = "Libros del autor " + autorSeleccionado;

        } else if ("resenias".equals(tipoSugerencia)) {
            String libroId = params.get("libroId");

            if (libroId != null) {
                int id = Integer.parseInt(libroId);
                libroBase = libroService.buscarPorId(id);

                librosSugeridos =
                    libroService.buscarLibrosPorReseniasSimilares(id);

                if (librosSugeridos.isEmpty()) {
                    mensajeSugerencia =
                        "No hay sugerencias basadas en reseñas para este libro";
                }
            }
        }
    }

    public LibroService getLibroService() {
        return libroService;
    }

    public void setLibroService(LibroService libroService) {
        this.libroService = libroService;
    }

    public ReseniaService getReseniaService() {
        return reseniaService;
    }

    public void setReseniaService(ReseniaService reseniaService) {
        this.reseniaService = reseniaService;
    }

    public String getTipoSugerencia() {
        return tipoSugerencia;
    }

    public void setTipoSugerencia(String tipoSugerencia) {
        this.tipoSugerencia = tipoSugerencia;
    }

    public String getAutorSeleccionado() {
        return autorSeleccionado;
    }

    public void setAutorSeleccionado(String autorSeleccionado) {
        this.autorSeleccionado = autorSeleccionado;
    }

    public Libro getLibroBase() {
        return libroBase;
    }

    public void setLibroBase(Libro libroBase) {
        this.libroBase = libroBase;
    }

    public List<Libro> getLibrosSugeridos() {
        return librosSugeridos;
    }

    public void setLibrosSugeridos(List<Libro> librosSugeridos) {
        this.librosSugeridos = librosSugeridos;
    }

    public String getMensajeSugerencia() {
        return mensajeSugerencia;
    }

    public void setMensajeSugerencia(String mensajeSugerencia) {
        this.mensajeSugerencia = mensajeSugerencia;
    }
    
}
