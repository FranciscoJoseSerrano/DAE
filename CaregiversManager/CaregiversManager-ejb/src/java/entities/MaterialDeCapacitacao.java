/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
    
    
   @ManyToMany
    @JoinTable(name = "NECESSIDADE_MATERIAL",
            joinColumns
            = @JoinColumn(name = "NECESSIDADE_CODE", referencedColumnName = "ID"),
            inverseJoinColumns
            = @JoinColumn(name = "MATERIAL_CODE", referencedColumnName = "ID"))
   private LinkedList<Necessidade> necessidades;
   
   public MaterialDeCapacitacao(){
        
    }
    
    public MaterialDeCapacitacao(Long id,SuporteMaterialDeCapacitacao suporte, TipoMaterialDeCapacitacao tipo,String link){
        this.id = id;
        this.suporte = suporte;
        this.tipoSuporte = tipo;
        this.link = link;
        this.necessidades=new LinkedList<>();
    }
    
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
