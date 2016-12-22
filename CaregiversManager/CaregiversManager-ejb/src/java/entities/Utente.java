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


/**
 *
 * @author joaos
 */


/*@NamedQueries({
@NamedQuery(name = "getAllMyUtentes",
    query = "SELECT s FROM Utente s WHERE s.cuidador.username = :username ORDER BY s.name")})*/

@Entity
public class Utente implements Serializable {
    
    @Id
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "PROFISSIONALSAUDE_CODE")
    private ProfissionalSaude profissional;
    
    @ManyToOne
    @JoinColumn(name = "CUIDADOR_CODE")
    private Cuidador cuidador;
    
    
    public Utente(){       
    }
    
    public void addCuidador(Cuidador cuidador){
        this.cuidador = cuidador;
    }
    
    public Utente(String name){
        this.name = name;
        this.cuidador = null;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Cuidador getCuidador(){
        return cuidador;
    }
    
    
    
}
