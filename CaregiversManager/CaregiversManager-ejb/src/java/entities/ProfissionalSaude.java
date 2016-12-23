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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joaos
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getAllProfissionais",
            query = "SELECT s FROM ProfissionalSaude s ORDER BY s.nome")})
public class ProfissionalSaude extends Utilizador implements Serializable {

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.REMOVE)
    private List<Utente> listUtentes;

    public ProfissionalSaude() {
        listUtentes = new LinkedList<>();
    }

    public ProfissionalSaude(String username, String nome, String password) {
        super(username, password, nome);
        listUtentes = new LinkedList<>();
    }
    
    
    

}
