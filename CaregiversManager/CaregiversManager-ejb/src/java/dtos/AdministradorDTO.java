/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author joaos
 */
public class AdministradorDTO implements Serializable {
    
    protected int id;
    protected String password;    
    protected String name;
    

    public AdministradorDTO() {
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public AdministradorDTO(int id, String name, String password) {
        this.id = id;
        this.password = password;
        this.name = name;  
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
        setId(0);
        setPassword(null);
        setName(null);
        
    }        

    
}
