/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Utilizador;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joaos
 */
@XmlRootElement(name = "Cuidador")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuidadorDTO extends UtilizadorDTO implements Serializable {
    
      
    public CuidadorDTO(){
        
    }
    
    public CuidadorDTO(String username, String password, String nome){
        super(username, password, nome);
    }

    @Override
    public void reset() {
        super.reset(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
