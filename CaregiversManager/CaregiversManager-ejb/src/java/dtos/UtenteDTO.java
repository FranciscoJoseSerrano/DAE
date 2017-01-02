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
@XmlRootElement(name = "Utente")
@XmlAccessorType(XmlAccessType.FIELD)
public class UtenteDTO {
    
    protected int id;
    protected String name;
   
   

    public UtenteDTO() {
    }

    public UtenteDTO(int id , String name) {
        this.id = id;
        this.name = name;
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void reset() {
        setId(0);
        setName(null);

    }
    
    @Override
    public String toString() {
        return "ID : " + this.id + "\n"
                +"Nome : " + this.name; //To change body of generated methods, choose Tools | Templates.
    }
        
    
}
