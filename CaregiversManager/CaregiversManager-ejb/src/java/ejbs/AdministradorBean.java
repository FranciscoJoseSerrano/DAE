/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;


import dtos.AdministradorDTO;
import entities.Administrador;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import javax.ws.rs.Path;


/**
 *
 * @author joaos
 */
@Stateless
public class AdministradorBean {

    @PersistenceContext
    private EntityManager em;

    
    public void create(int id, String name, String password) {
        try {
            if (em.find(Administrador.class, id) != null) {
                return;
            }
            em.persist(new Administrador(id, name, password));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void remove(int id) throws EntityDoesNotExistsException {
        try {
            Administrador administrador = em.find(Administrador.class, id);
            if (administrador == null) {
                throw new EntityDoesNotExistsException("There is no Administrador with that id.");
            }        
            em.remove(administrador);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void update(AdministradorDTO administradorDTO) 
        throws EntityDoesNotExistsException, MyConstraintViolationException{
        try {
          
            Administrador administrador = em.find(Administrador.class, administradorDTO.getId());
            if (administrador == null) {
                throw new EntityDoesNotExistsException("There is no administrador with that username.");
            }

            administrador.setPassword(administradorDTO.getPassword());
            administrador.setName(administradorDTO.getName());
            
            em.merge(administrador);       
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
       public List<AdministradorDTO> getAllAdministradores() {
        try {
            List<Administrador> administradores = em.createNamedQuery("getAllAdministradores").getResultList();
            return getAdministradorDTOS(administradores);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    

     
    //AUXILIARES
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
