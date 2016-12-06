/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.AdministradorDTO;
import entities.Administrador;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author joaos
 */
@Stateless
@Path("/administradores")
public class AdministradorBean {

    @PersistenceContext
    private EntityManager em;

    public void create(int id, String password, String name) {
        try {
            if (em.find(Administrador.class, id) != null) {
                return;
            }
            em.persist(new Administrador(id, password, name));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("all")
    public List<AdministradorDTO> getAll() {
        try {
            List<Administrador> administradores = (List<Administrador>) em.createNamedQuery("getAllAdministradores").getResultList();
            return getAdministradorDTOS(administradores);
        }catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    
    private List<AdministradorDTO> getAdministradorDTOS(List<Administrador> admnistradores){
        List<AdministradorDTO> administradorDTOs = new ArrayList<>();
        
        for(Administrador administrador : admnistradores){
            administradorDTOs.add(transformDTO(administrador));
        }
        return administradorDTOs;
        
    }

    private AdministradorDTO transformDTO(Administrador administrador) {
       return new AdministradorDTO(administrador.getId(), administrador.getName(), null);
    }
    
    
    
    
}
