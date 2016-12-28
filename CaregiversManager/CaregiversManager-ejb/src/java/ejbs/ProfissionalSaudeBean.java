/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;
import dtos.ProfissionalSaudeDTO;
import entities.ProfissionalSaude;
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

/**
 *
 * @author joaos
 */
@Stateless
public class ProfissionalSaudeBean {

    @PersistenceContext
    private EntityManager em;
    
    public void create(String username, String name, String password) {
        try {
            if (em.find(ProfissionalSaude.class, username) != null) {
                return;
            }
            em.persist(new ProfissionalSaude(username, name, password));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public ProfissionalSaudeDTO getProfissionalSaude(String username) {
        try {
            ProfissionalSaude profissional = em.find(ProfissionalSaude.class, username);
            if (profissional == null) {
                return null;
            }
            return transformDTO(profissional);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void remove(String username) {
        try {
            ProfissionalSaude profissional = em.find(ProfissionalSaude.class, username);
            if (profissional == null) {
                throw new EntityDoesNotExistsException("Não existe nenhum Profissional de Saúde com esse username");
            }
            em.remove(profissional);
            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void update(ProfissionalSaudeDTO profissionalSaudeDTO)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            
            ProfissionalSaude profissional = em.find(ProfissionalSaude.class, profissionalSaudeDTO.getUsername());
            if (profissional == null) {
                throw new EntityDoesNotExistsException("Não existe nenhum Profissional de Saúde com esse username");
            }
            
            profissional.setPassword(profissionalSaudeDTO.getPassword());
            profissional.setName(profissionalSaudeDTO.getNome());
            
            em.merge(profissional);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    
    public List<ProfissionalSaudeDTO> getAllProfissionais() {
        try {
            List<ProfissionalSaude> profissionais = em.createNamedQuery("getAllProfissionais").getResultList();
            return getProfissionalSaudeDTOS(profissionais);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    

    //AUXILIARES
    private List<ProfissionalSaudeDTO> getProfissionalSaudeDTOS(List<ProfissionalSaude> profissionais) {
        List<ProfissionalSaudeDTO> profissionaisDTOs = new ArrayList<>();
        
        for (ProfissionalSaude profissional : profissionais) {
            profissionaisDTOs.add(transformDTO(profissional));
        }
        return profissionaisDTOs;
        
    }
    
    private ProfissionalSaudeDTO transformDTO(ProfissionalSaude profissional) {
        return new ProfissionalSaudeDTO(profissional.getUsername(), profissional.getName(), null);
    }
    
}
