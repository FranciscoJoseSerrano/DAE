/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joaos
 */
@Entity
public class Utente implements Serializable {
    
    @Id
    private String name;
    
   /* @ManyToOne
    @JoinColumn(name = "CUIDADOR_CODE")
    private Cuidador cuidador;*/
    
    
    public Utente(){
        
    }
    
    public Utente(String name){
        this.name = name;
      //  this.cuidador = new Cuidador();
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
