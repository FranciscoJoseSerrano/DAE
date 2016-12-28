/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author joaos
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getAllAdministradores",
            query = "SELECT s FROM Administrador s ORDER BY s.nome")})
public class Administrador extends Utilizador implements Serializable {


    public Administrador() {

    }

    public Administrador(String username, String name, String password) {
        super(username,password,name,GROUP.Administrador);
    }

}
