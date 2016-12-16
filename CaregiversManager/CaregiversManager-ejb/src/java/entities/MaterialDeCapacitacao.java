/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Francisco
 */
@Entity
public class MaterialDeCapacitacao implements Serializable {

    @Id
    private Long id;
    
    private SuporteMaterialDeCapacitacao suporte;
    
    private TipoMaterialDeCapacitacao tipoSuporte;
    
    private String link;
    
    private byte[] conteudo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tipo: "+tipoSuporte+"//Suporte: "+suporte;
    }
    
    public SuporteMaterialDeCapacitacao getSuporte(){
        return suporte;
    }
    
    public void setSuporte(SuporteMaterialDeCapacitacao s){
        this.suporte = s;
    }
    
    public TipoMaterialDeCapacitacao getTipo(){
        return tipoSuporte;
    }
    
    public void setTipo(TipoMaterialDeCapacitacao t){
        this.tipoSuporte = t;
    }
    
    public String getLink(){
        return link;
    }
    
    public void setLink(String s){
        this.link = s;
    }
    
    public byte[] getConteudo(){
        return conteudo;
    }
    
    public void setConteudo(byte[] c){
        this.conteudo = c;
    }
    
}
