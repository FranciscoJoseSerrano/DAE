/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.CuidadorDTO;
import entities.Cuidador;
import exceptions.EntityAlreadyExistsException;
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
 * @author Francisco
 */
@Stateless
public class CuidadorBean {

    @PersistenceContext
    private EntityManager em;

    public void create(String username, String nome, String password) {
        try {
            if (em.find(Cuidador.class, username) != null) {
                throw new EntityAlreadyExistsException("Já existe um cuidador com esse username");
            }
            em.persist(new Cuidador(username, nome, password));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void remove(String username) {
        try {
            Cuidador c = em.find(Cuidador.class, username);
            if (c == null) {
                throw new EntityDoesNotExistsException("Não existe nenhum cuidador com esse username");
            }
            em.remove(c);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void update(CuidadorDTO c)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {

            Cuidador cuidador = em.find(Cuidador.class, c.getUsername());
            if (cuidador == null) {
                throw new EntityDoesNotExistsException("Não existe cuidador com esse username.");
            }

            cuidador.setPassword(c.getPassword());
            cuidador.setName(c.getNome());

            em.merge(cuidador);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<CuidadorDTO> getCuidadores() {
        try {
            List<Cuidador> cuidadores = em.createNamedQuery("getAllCuidadores").getResultList();
            return getCuidadoresDTOS(cuidadores);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public boolean existsCuidador(String username) {
        if (em.find(Cuidador.class, username) == null) {
            return false;
        }
        return true;
    }

    public CuidadorDTO getCuidador(String username) {
        try {
            Cuidador c = em.find(Cuidador.class, username);
            if (c != null) {
                CuidadorDTO dto = new CuidadorDTO(c.getUsername(), c.getPassword(), c.getName());
                return dto;
            }
        } catch (EJBException e) {
            throw new EJBException(e.getMessage());
        }

        return null;
    }

//AUXILIARES
    private List<CuidadorDTO> getCuidadoresDTOS(List<Cuidador> cuidadores) {
        List<CuidadorDTO> cuidadoresDTOs = new ArrayList<>();

        for (Cuidador c : cuidadores) {
            cuidadoresDTOs.add(transformDTO(c));
        }
        return cuidadoresDTOs;

    }

    private CuidadorDTO transformDTO(Cuidador c) {
        return new CuidadorDTO(c.getUsername(), null, c.getName());
    }

}
