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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marta
 */
@Entity
public class Necessidade implements Serializable {
    
    @Id
    private Long id;
    @NotNull
    private String nome;
    private String descrição;
   
   @ManyToMany(mappedBy = "necessidades")
   private LinkedList<MaterialDeCapacitacao> materiais;
   
    @ManyToMany
    @JoinTable(name = "UTENTE_NECESSIDADE",
            joinColumns
            = @JoinColumn(name = "UTENTE_CODE", referencedColumnName = "ID"),
            inverseJoinColumns
            = @JoinColumn(name = "NECESSIDADE_CODE", referencedColumnName = "ID"))
   private LinkedList<Utente> utentes;
   
   public Necessidade(){
       
   }

    public Necessidade(Long id, String nome, String descrição) {
        this.id = id;
        this.nome = nome;
        this.descrição = descrição;
        this.materiais=new LinkedList<>();
        this.utentes=new LinkedList<>();
                
    }

    public LinkedList<Utente> getUtentes() {
        return utentes;
    }

    public void setUtentes(LinkedList<Utente> utentes) {
        this.utentes = utentes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public LinkedList<MaterialDeCapacitacao> getMateriais() {
        return materiais;
    }

    public void setMateriais(LinkedList<MaterialDeCapacitacao> materiais) {
        this.materiais = materiais;
    }
   
    
    
}
    
