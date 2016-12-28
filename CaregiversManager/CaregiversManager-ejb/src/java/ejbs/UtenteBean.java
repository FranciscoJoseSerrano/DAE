/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.ProfissionalSaudeDTO;
import dtos.UtenteDTO;
import entities.Cuidador;
import entities.ProfissionalSaude;
import entities.Utente;
import exceptions.EntityDoesNotExistsException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author joaos
 */
//////ELEMENT COLLENCTION NÂO ESQUECER !!!!///////
@Stateless
@Path("/utentes")
public class UtenteBean {

    private EntityManager em;

    public void create(int id, String username) {
        try {
            em.persist(new Utente(id,username));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void giveUtenteToCuidador(int id, String usernameCuidador)
            throws EntityDoesNotExistsException {
        try {

            Utente utente = em.find(Utente.class, id);
            if (utente == null) {
                throw new EntityDoesNotExistsException("Não existe Utente com esse username.");
            }

            Cuidador cuidador = em.find(Cuidador.class, usernameCuidador);
            if (cuidador == null) {
                throw new EntityDoesNotExistsException("Não existe Cuidador com esse username");
            }

            if (utente.getCuidador() != null) {
                throw new Exception("O Utente já contém cuidador");
            }

            cuidador.addUtente(utente);
            utente.addCuidador(cuidador);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    
    
    //PARA CUIDADOR//
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("myUtentes")
    public List<UtenteDTO> getMyUtentes(String username) throws EntityDoesNotExistsException {
        try {
            Cuidador cuidador = em.find(Cuidador.class, username);
            if (cuidador == null) {
                throw new EntityDoesNotExistsException("Não existe Cuidador com esse nome.");
            }
            List<Utente> utentes = (List<Utente>) cuidador.getMyUtentes();
            return getUtentesDTOS(utentes);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    

    //AUXILIARES
    private List<UtenteDTO> getUtentesDTOS(List<Utente> utentes) {
        List<UtenteDTO> utentesDTOs = new ArrayList<>();

        for (Utente utente : utentes) {
            utentesDTOs.add(transformDTO(utente));
        }
        return utentesDTOs;

    }

    private UtenteDTO transformDTO(Utente utente) {
        return new UtenteDTO(utente.getId(),utente.getName());
    }

}
