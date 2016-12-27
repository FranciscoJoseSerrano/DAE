/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

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
        super(username,password,name);
    }

}
