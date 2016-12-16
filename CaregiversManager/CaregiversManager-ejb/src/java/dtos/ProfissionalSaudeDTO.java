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
public class ProfissionalSaudeDTO {
    protected String username;
    protected String password;
    protected String name;

    public ProfissionalSaudeDTO() {
    }

    public ProfissionalSaudeDTO(String username, String name, String password) {
        this.username = username;
        this.password = password;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void reset() {
        setUsername(null);
        setPassword(null);
        setName(null);

    }
}
