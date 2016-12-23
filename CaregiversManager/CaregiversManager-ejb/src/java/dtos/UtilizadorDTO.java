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
public class UtilizadorDTO implements Serializable {

    protected String nome;
    protected String username;
    protected String password;

    public UtilizadorDTO() {
    }

    public UtilizadorDTO(String username, String password, String nome) {
        this.username = username;
        this.password = password;
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
    
    
    public void reset(){
        setUsername(null);
        setPassword(null);
        setNome(null);
    }

}
