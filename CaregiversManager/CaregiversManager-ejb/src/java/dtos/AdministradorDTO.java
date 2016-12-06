/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joaos
 */
@XmlRootElement(name = "Administrador")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdministradorDTO implements Serializable{

    protected int id;
    protected String name;
    protected String password;

    public AdministradorDTO() {

    }

    public AdministradorDTO(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void reset() {     
        setPassword(null);
        setName(null);
        setPassword(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
