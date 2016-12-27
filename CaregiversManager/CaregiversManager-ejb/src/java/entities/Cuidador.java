
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
   
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.REMOVE)
    private LinkedList<Utente> utentes;
    
    
    @OneToMany(mappedBy = "cuidador", cascade=CascadeType.REMOVE)         
    LinkedList<ProcedimentoAplicado> procedimentosAplicados;


    
    public Cuidador(){
        
    }

    public Cuidador(String nome, String password) {
        this.username = nome;
        this.password = password;
        utentes = new LinkedList<>();
    }

    public Cuidador(String username, String nome, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public LinkedList<Utente> getMyUtentes() {
        return utentes;
    }
    
    public void addUtente(Utente utente){
        utentes.add(utente);
    }
    
        public LinkedList<Utente> getUtentes() {
        return utentes;
    }



    public LinkedList<ProcedimentoAplicado> getProcedimentosAplicados() {
        return procedimentosAplicados;
    }

    public void setProcedimentosAplicados(LinkedList<ProcedimentoAplicado> procedimentosAplicados) {
        this.procedimentosAplicados = procedimentosAplicados;
    }

    public void setName(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

   
}
