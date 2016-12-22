/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joaos
 */
@Entity
public class Cuidador implements Serializable {


    @Id
    private String username;
    @NotNull
    private String password;
    
    @ManyToMany
    @JoinTable(name = "CUIDADOR_UTENTE",
            joinColumns
            = @JoinColumn(name = "CUIDADOR_USERNAME", referencedColumnName = "USERNAME"),
            inverseJoinColumns
            = @JoinColumn(name = "UTENTE_NAME", referencedColumnName = "NAME"))
    private List<Utente> listUtentes;
    
    public Cuidador(){
        
    }

    public Cuidador(String nome, String password) {
        this.username = nome;
        this.password = password;
        listUtentes = new LinkedList<>();
    }
   
  
    @Override
    public String toString() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
