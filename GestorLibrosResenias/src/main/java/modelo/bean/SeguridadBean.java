/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package modelo.bean;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.IOException;

/**
 *
 * @author Portege Z930
 */
@Named(value = "seguridadBean")
@RequestScoped
public class SeguridadBean {
    //clase para controlar el acceso de los usuarios
@Inject
    private LoginBean loginBean;

    @PostConstruct
    public void comprobarLogin() {
        if (loginBean == null || !loginBean.isLogeado()) {
            redirigirLogin();
        }
    }

    public void comprobarAdmin() {
        if (!"Administrador".equalsIgnoreCase(loginBean.getRol())) {
            redirigirLogin();
        }
    }

    private void redirigirLogin() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
        } catch (IOException e) {
        }
    }
    
}
