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
@XmlRootElement(name = "ProfissionalSaude")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProfissionalSaudeDTO extends UtilizadorDTO{
    

    public ProfissionalSaudeDTO() {
    }

    public ProfissionalSaudeDTO(String username, String nome, String password) {
        super(username, password, nome);
    }

    @Override
    public void reset() {
        super.reset(); //To change body of generated methods, choose Tools | Templates.
    }

}
