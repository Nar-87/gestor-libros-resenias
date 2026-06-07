/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;
import modelo.dao.ReseniaDAO;
import modelo.entidades.Resenia;

/**
 *
 * @author Portege Z930
 */
@Stateless
public class ReseniaService {

    @Inject
    private ReseniaDAO reseniaDAO;

    public List<Resenia> obtenerTodas() {
        return reseniaDAO.findAll();
    }

    public List<Resenia> obtenerPorLibro(int idLibro) {
        return reseniaDAO.findByLibro(idLibro);
    }

    public void crearResenia(Resenia resenia) {
        reseniaDAO.insert(resenia);
    }

    public void actualizarResenia(Resenia resenia) {
        reseniaDAO.update(resenia);
    }

    public void eliminarResenia(int idResenia) {
        reseniaDAO.delete(idResenia);
    }
    
}
