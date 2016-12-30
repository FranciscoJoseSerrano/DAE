/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Cuidador;
import entities.MaterialDeCapacitacao;
import entities.Necessidade;
import entities.Utente;
import exceptions.EntityDoesNotExistsException;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author joaos
 */
@Stateless
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
}
