/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Marta
 */
@Entity
public class ProcedimentoAplicado implements Serializable {

   
    @Id
   private Long id;
    
   
   @ManyToOne
   @JoinColumn(name = "CUIDADOR_CODE")
   private Cuidador cuidador;
   
    @ManyToOne
   @JoinColumn(name = "UTENTE_CODE")
    private Utente utente;
    
   @OneToOne
    private MaterialDeCapacitacao procedimento;
   
   public ProcedimentoAplicado(){
       
   }

    public ProcedimentoAplicado(Long id, Cuidador cuidador, Utente utente, MaterialDeCapacitacao procedimento) {
        this.id = id;
        this.cuidador = cuidador;
        this.utente = utente;
        this.procedimento = procedimento;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    public MaterialDeCapacitacao getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(MaterialDeCapacitacao procedimento) {
        this.procedimento = procedimento;
    }
   
    
   
    
}
