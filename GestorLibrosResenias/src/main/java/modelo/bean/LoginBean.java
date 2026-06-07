/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package modelo.bean;

import cliente.LoginClient;
import com.ddi.loginxperiment.dto.AutorizacionDTO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Portege Z930
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private LoginClient loginClient;
    private String tipoLogin;

    private String usuario;
    private String password;

    private Integer idusu;
    private String rol;
    private boolean logeado;

    public String login() {
        AutorizacionDTO auth = loginClient.login(usuario, password);

        if (auth != null && auth.isLogeado()) {
            this.idusu = auth.getIdusu();
            this.rol = auth.getRol();
            this.usuario = auth.getNombre();
            this.logeado = true;

            if ("Administrador".equalsIgnoreCase(rol)) {
                return "/libros?faces-redirect=true";
            } else {
                return "/resenias?faces-redirect=true";
            }
        }

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                             "Acceso denegado",
                             "Usuario o contraseña incorrectos"));

        return null; 
    }

    public String logout() {
        logeado = false;
        idusu = null;
        rol = null;
        usuario = null;
        password = null;
        return "/login?faces-redirect=true";
    }
    
    public LoginClient getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(LoginClient loginClient) {
        this.loginClient = loginClient;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdusu() {
        return idusu;
    }

    public void setIdusu(Integer idusu) {
        this.idusu = idusu;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }
    
    public String getTipoLogin() {
        return tipoLogin;
    }

    public void setTipoLogin(String tipoLogin) {
        this.tipoLogin = tipoLogin;
    }
 
}
