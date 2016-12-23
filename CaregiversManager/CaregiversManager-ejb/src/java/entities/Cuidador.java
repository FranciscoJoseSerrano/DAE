/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author joaos
 */
@Entity
public class Cuidador extends Utilizador implements Serializable {

    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.REMOVE)
    private List<Utente> listUtentes;
    
    public Cuidador(){
        this.listUtentes = new LinkedList<>();
    }

    public Cuidador(String username, String nome ,String password) {
        super(username,password, nome);
        listUtentes = new LinkedList<>();
    }
   
  
    public List<Utente> getMyUtentes() {
        return listUtentes;
    }
    
    public void addUtente(Utente utente){
        listUtentes.add(utente);
    }

 /*   public void setUtentes(LinkedList<Utente> utentes) {
        this.utentes = utentes;
    }*/
}
