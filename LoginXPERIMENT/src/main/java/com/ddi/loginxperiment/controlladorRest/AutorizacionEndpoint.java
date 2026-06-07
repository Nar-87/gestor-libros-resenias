package com.ddi.loginxperiment.controlladorRest;

import com.ddi.loginxperiment.dto.AutorizacionDTO;
import com.ddi.loginxperiment.dto.UsuarioDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.dao.UsuarioDAO;
import modelo.entity.Usuario;


    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public class AutorizacionEndpoint {

        @Inject
        private UsuarioDAO usuarioDAO;

        @POST
        @Path("/login")
        public Response login(UsuarioDTO dto) {

            Usuario u = usuarioDAO.login(dto.getNombre(), dto.getContrasena());

            if (u == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(new AutorizacionDTO(false, null, null, null))
                        .build();
            }

            return Response.ok(
                new AutorizacionDTO(
                    true,
                    u.getIdusu(),
                    u.getNombcusu(),
                    u.getPerfil().getDesperfil()
                )
            ).build();
        }
    }
