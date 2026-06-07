/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import com.ddi.loginxperiment.dto.AutorizacionDTO;
import com.ddi.loginxperiment.dto.UsuarioDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Portege Z930
 */
@ApplicationScoped
public class LoginClient {

    //conexión mediante http del otro proyecto
    private static final String LOGIN_URL =
        "http://localhost:8080/LoginXPERIMENT/api/auth/login";

    public AutorizacionDTO login(String usuario, String password) {

        Client client = ClientBuilder.newClient();

        UsuarioDTO dto = new UsuarioDTO(usuario, password);

        try (Response response = client
                .target(LOGIN_URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON))) {
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(AutorizacionDTO.class);
            }
            return null;
        } finally {
            client.close();
        }
    }
}
