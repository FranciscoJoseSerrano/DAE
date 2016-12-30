/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.CuidadorDTO;
import dtos.MaterialDeCapacitacaoDTO;
import entities.Cuidador;
import entities.MaterialDeCapacitacao;
import entities.SuporteMaterialDeCapacitacao;
import entities.TipoMaterialDeCapacitacao;
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
public class MaterialDeCapacitacaoBean {

    @PersistenceContext
    private EntityManager em;

    public void create(String descricao, SuporteMaterialDeCapacitacao suporte, TipoMaterialDeCapacitacao tipo, String link) {
        try {
            /* if (em.find(MaterialDeCapacitacao.class, id) != null) {
                throw new EntityAlreadyExistsException("Já existe um cuidador com esse username");
            }*/
            em.persist(new MaterialDeCapacitacao(descricao, suporte, tipo, link));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void remove(Long id) {
        try {
            MaterialDeCapacitacao material = em.find(MaterialDeCapacitacao.class, id);
            if (material == null) {
                throw new EntityDoesNotExistsException("Não existe nenhum material de capacitação com o id especificado");
            }
            em.remove(material);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void update(MaterialDeCapacitacaoDTO material)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            MaterialDeCapacitacao m = em.find(MaterialDeCapacitacao.class, material.getId());
            if (m == null) {
                throw new EntityDoesNotExistsException("Não existe material de capacitação com o id especificado.");
            }
            m.setDescricao(material.getDescricao());
            m.setSuporte(material.getSuporte());
            m.setTipo(material.getTipoSuporte());
            m.setLink(material.getLink());
            em.merge(m);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<MaterialDeCapacitacaoDTO> getMateriaisDeCapacitacao() {
        try {
            List<MaterialDeCapacitacao> materiais = em.createNamedQuery("getAllMateriaisDeCapacitacao").getResultList();
            return getMateriaisDTO(materiais);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public boolean existsMaterial(Long id) {
        if (em.find(MaterialDeCapacitacao.class, id) == null) {
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
    private MaterialDeCapacitacaoDTO transformDTO(MaterialDeCapacitacao m) {
        return new MaterialDeCapacitacaoDTO(m.getId(), m.getDescricao(), m.getSuporte(), m.getTipo(), m.getLink());
    }

    private List<MaterialDeCapacitacaoDTO> getMateriaisDTO(List<MaterialDeCapacitacao> materiais) {
        List<MaterialDeCapacitacaoDTO> materiaisDTO = new ArrayList<>();

        for (MaterialDeCapacitacao m : materiais) {
            materiaisDTO.add(transformDTO(m));
        }
        return materiaisDTO;

    }

    public MaterialDeCapacitacaoDTO getMaterialCapacitacaoByID(Long id) {
        try {
            MaterialDeCapacitacao c = em.find(MaterialDeCapacitacao.class, id);
            if (c != null) {
                MaterialDeCapacitacaoDTO dto = new MaterialDeCapacitacaoDTO(c.getId(), c.getDescricao(), c.getSuporte(), c.getTipo(), c.getLink());
                return dto;
            }
        } catch (EJBException e) {
            throw new EJBException(e.getMessage());
        }

        return null;
    }

    public List<MaterialDeCapacitacaoDTO> getMaterialCapacitacaoByDescricao(String descricao) {
        try {
            List<MaterialDeCapacitacao> list = em.createNamedQuery("getAllMateriaisByDescricao").setParameter("descricao", descricao).getResultList();
            List<MaterialDeCapacitacaoDTO> aux = new ArrayList();
            for (MaterialDeCapacitacao m : list) {
                transformDTO(m);
                list.add(m);
            }
            if (aux.size() > 0) {
                return aux;
            }

        } catch (EJBException e) {
            throw new EJBException(e.getMessage());
        }

        return null;
    }

}
