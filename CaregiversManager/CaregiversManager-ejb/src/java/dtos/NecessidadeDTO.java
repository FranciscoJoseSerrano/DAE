/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joaos
 */
@XmlRootElement(name = "Necessidade")
@XmlAccessorType(XmlAccessType.FIELD)
public class NecessidadeDTO {
    
    protected int id;
    protected String nome;
    protected String descricao;
    
    public NecessidadeDTO() {
        
    }
    
    public NecessidadeDTO(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void reset() {
        setDescricao(null);
        setId(0);
        setNome(null);
        
    }
    
}
