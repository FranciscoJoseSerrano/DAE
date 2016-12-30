/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.SuporteMaterialDeCapacitacao;
import entities.TipoMaterialDeCapacitacao;
import java.io.Serializable;

import javax.persistence.Entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marta
 */
@XmlRootElement(name = "MaterialDeCapacitacao")
@XmlAccessorType(XmlAccessType.FIELD)
public class MaterialDeCapacitacaoDTO implements Serializable {

    protected Long id;
    protected String descricao;
    protected SuporteMaterialDeCapacitacao suporte;
    protected TipoMaterialDeCapacitacao tipoSuporte;
    protected String link;
    protected byte[] conteudo;

    public MaterialDeCapacitacaoDTO() {

    }

    public MaterialDeCapacitacaoDTO(Long id, String descricao, SuporteMaterialDeCapacitacao suporte, TipoMaterialDeCapacitacao tipo, String link) {
        this.id = id;
        this.descricao = descricao;
        this.suporte = suporte;
        this.tipoSuporte = tipo;
        this.link = link;
        this.conteudo = conteudo;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoMaterialDeCapacitacao getTipoSuporte() {
        return tipoSuporte;
    }

    public void setTipoSuporte(TipoMaterialDeCapacitacao tipoSuporte) {
        this.tipoSuporte = tipoSuporte;
    }

    public SuporteMaterialDeCapacitacao getSuporte() {
        return suporte;
    }

    public void setSuporte(SuporteMaterialDeCapacitacao s) {
        this.suporte = s;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String s) {
        this.link = s;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] c) {
        this.conteudo = c;
    }

    public void reset() {
        this.setId(null);
        this.setConteudo(null);
        this.setLink(null);
        this.setSuporte(null);
        this.setTipoSuporte(null);

    }

    @Override
    public String toString() {
        return "Tipo: " + tipoSuporte + "//Suporte: " + suporte;
    }
}
