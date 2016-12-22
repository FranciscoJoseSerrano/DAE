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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joaos
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getAllProfissionais",
            query = "SELECT s FROM ProfissionalSaude s ORDER BY s.name")})
public class ProfissionalSaude implements Serializable {

    @Id
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String password;
    
    @ManyToMany
    @JoinTable(name = "PROFISSIONAL_UTENTES",
            joinColumns
            = @JoinColumn(name = "PROFISSIONALSAUDE_USERANAME", referencedColumnName = "USERNAME"),
            inverseJoinColumns
            = @JoinColumn(name = "UTENTE_NAME", referencedColumnName = "NAME"))
    private List<Utente> listUtentes;

    public ProfissionalSaude() {
        listUtentes = new LinkedList<>();
    }

    public ProfissionalSaude(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = hashPassword(password);
        listUtentes = new LinkedList<>();
    }

    private String hashPassword(String password) {
        char[] encoded = null;
        try {
            ByteBuffer passwdBuffer
                    = Charset.defaultCharset().encode(CharBuffer.wrap(password));
            byte[] passwdBytes = passwdBuffer.array();
            MessageDigest mdEnc = MessageDigest.getInstance("SHA-256");
            mdEnc.update(passwdBytes, 0, password.toCharArray().length);
            encoded = new BigInteger(1, mdEnc.digest()).toString(16).toCharArray();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(encoded);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
