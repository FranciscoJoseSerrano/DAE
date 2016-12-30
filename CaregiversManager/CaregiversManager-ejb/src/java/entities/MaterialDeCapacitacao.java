/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Francisco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getAllMateriaisDeCapacitacao",
            query = "SELECT m FROM MaterialDeCapacitacao m")})
    /*@NamedQuery(name = "getAllMateriaisByDescricao",
            query = "SELECT m FROM MaterialDeCapacitacao m WHERE descricao LIKE :descricao")})*/
public class MaterialDeCapacitacao implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "MATERIAL_SEQ")
    @Column(name = "ID")
    private Long id;

    private String descricao;
    private SuporteMaterialDeCapacitacao suporte;
    private TipoMaterialDeCapacitacao tipoSuporte;
    private String link;
    private byte[] conteudo;

    @ManyToMany
    @JoinTable(name = "NECESSIDADE_MATERIAL",
            joinColumns
            = @JoinColumn(name = "NECESSIDADE_CODE", referencedColumnName = "ID"),
            inverseJoinColumns
            = @JoinColumn(name = "MATERIAL_CODE", referencedColumnName = "ID"))
    private LinkedList<Necessidade> necessidades;

    public MaterialDeCapacitacao() {

    }

    public MaterialDeCapacitacao(String descricao, SuporteMaterialDeCapacitacao suporte, TipoMaterialDeCapacitacao tipo, String link) {
        this.descricao = descricao;
        this.suporte = suporte;
        this.tipoSuporte = tipo;
        this.link = link;
        this.necessidades = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SuporteMaterialDeCapacitacao getSuporte() {
        return suporte;
    }

    public void setSuporte(SuporteMaterialDeCapacitacao s) {
        this.suporte = s;
    }

    public TipoMaterialDeCapacitacao getTipo() {
        return tipoSuporte;
    }

    public void setTipo(TipoMaterialDeCapacitacao t) {
        this.tipoSuporte = t;
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

    public LinkedList<Necessidade> getNecessidades() {
        return necessidades;
    }
    
    public void addNecessidade(Necessidade n){
        this.necessidades.add(n);
    }

    public void setNecessidades(LinkedList<Necessidade> necessidades) {
        this.necessidades = necessidades;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipoSuporte + "//Suporte: " + suporte;
    }

}
