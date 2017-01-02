/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.NecessidadeDTO;
import dtos.ProfissionalSaudeDTO;
import entities.MaterialDeCapacitacao;
import entities.Necessidade;
import entities.ProfissionalSaude;
import entities.Utente;
import exceptions.EntityDoesNotExistsException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author joaos
 */
@Stateless
@Path("/necessidades")
public class NecessidadeBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    public void create(int id, String nome, String descrição) {
        try {
            Necessidade necessidade = em.find(Necessidade.class, id);
            if (necessidade == null) {
                em.persist(new Necessidade(id, nome, descrição));
            }
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void giveMateriaisNecessidades(int idNecessidade, Long idMaterial)
            throws EntityDoesNotExistsException {
        try {

            Necessidade necessidade = em.find(Necessidade.class, idNecessidade);
            if (necessidade == null) {
                throw new EntityDoesNotExistsException("Não existe Necessidade com esse id.");
            }

            MaterialDeCapacitacao material = em.find(MaterialDeCapacitacao.class, idMaterial);
            if (material == null) {
                throw new EntityDoesNotExistsException("Não existe Material de Capacitação com esse id");
            }

            material.addNecessidade(necessidade);
            necessidade.addMaterial(material);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
      public LinkedList<NecessidadeDTO> getAllNecessidades() {
        try {
            LinkedList<Necessidade> necessidades = (LinkedList<Necessidade>) em.createNamedQuery("getAllNecessidades").getResultList();
            return getNecessidadesDTOS(necessidades);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
      
      
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("necessidadeUtente/{idUtente}/")
    public List<NecessidadeDTO> getNecessidadesUtente(@PathParam("idUtente") int idUtente) throws EntityDoesNotExistsException {
        try {
            Utente utente = em.find(Utente.class, idUtente);
            if (utente == null) {
                throw new EntityDoesNotExistsException("Student does not exists.");
            }

            return getNecessidadesDTOS(utente.getNecessidades());
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    
    public List<NecessidadeDTO> getNecessidadesUtenteNormal(int idUtente) throws EntityDoesNotExistsException {
        try {
            Utente utente = em.find(Utente.class, idUtente);
            if (utente == null) {
                throw new EntityDoesNotExistsException("Student does not exists.");
            }

            return getNecessidadesDTOS(utente.getNecessidades());
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    
    
    
    private LinkedList<NecessidadeDTO> getNecessidadesDTOS(List<Necessidade> necessidades) {
        LinkedList<NecessidadeDTO> necessidadeDTOs = new LinkedList<>();

        for (Necessidade necessidade : necessidades) {
            necessidadeDTOs.add(transformDTO(necessidade));
        }
        return necessidadeDTOs;

    }

    private NecessidadeDTO transformDTO(Necessidade necessidade) {
        return new NecessidadeDTO(necessidade.getId(), necessidade.getNome(), necessidade.getDescricao());
    }

}
