//clase usada en el Gestor


//package com.ddi.loginxperiment.beans;
//
//import com.ddi.loginxperiment.dto.AutorizacionDTO;
//import com.ddi.loginxperiment.dto.UsuarioDTO;
//import com.ddi.loginxperiment.servicio.AutorizacionService;
//import jakarta.ejb.EJB;
//import jakarta.enterprise.context.SessionScoped;
//import jakarta.inject.Named;
//import java.io.Serializable;
//
//@Named("beanForm")
//@SessionScoped
//public class FormularioBean implements Serializable {
//
//    @EJB
//    AutorizacionService servicioAutorizacion;
//
//    private UsuarioDTO usuarioDTO = new UsuarioDTO();
//    private Integer idUsuario;
//    private String rol;
//    private boolean logeado;
//
//    public String login() {
//
//        AutorizacionDTO auth =
//                servicioAutorizacion.login(usuarioDTO.getNombre(), usuarioDTO.getContrasena());
//
//        if (auth != null && auth.isLogeado()) {
//            this.logeado = true;
//            this.idUsuario = auth.getIdusu();
//            this.rol = auth.getRol();
//
//            if ("Administrador".equalsIgnoreCase(rol)) {
//                return "/admin.xhtml?faces-redirect=true";
//            } else {
//                return "/usuario.xhtml?faces-redirect=true";
//            }
//        }
//        return null;
//    }
//    
//    public AutorizacionService getServicioAutorizacion() {
//        return servicioAutorizacion;
//    }
//
//    public void setServicioAutorizacion(AutorizacionService servicioAutorizacion) {
//        this.servicioAutorizacion = servicioAutorizacion;
//    }
//
//    public UsuarioDTO getUsuarioDTO() {
//        return usuarioDTO;
//    }
//
//    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
//        this.usuarioDTO = usuarioDTO;
//    }
//
//    public String getRol() {
//        return rol;
//    }
//
//    public void setRol(String rol) {
//        this.rol = rol;
//    }
//
//    public boolean isLogeado() {
//        return logeado;
//    }
//
//    public void setLogeado(boolean logeado) {
//        this.logeado = logeado;
//    }
//    
//    
//}
