package entities;

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joaos
 */
@Entity
public class Cuidador implements Serializable {

    @Id
    private String username;
    @NotNull
    private String password;
    
    @NotNull
    private String nome;

    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.REMOVE)
    private LinkedList<Utente> utentes;

    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.REMOVE)
    LinkedList<ProcedimentoAplicado> procedimentosAplicados;

    public Cuidador() {
        utentes = new LinkedList<>();
        procedimentosAplicados = new LinkedList<>();
    }

    public Cuidador(String username, String password) {
        this.username = username;
        this.password = password;
        utentes = new LinkedList<>();
        procedimentosAplicados = new LinkedList<>();
    }

    public Cuidador(String username, String nome, String password) {
        this.username = username;
        this.nome = nome;
        this.password = password;
        utentes = new LinkedList<>();
        procedimentosAplicados = new LinkedList<>();
    }

    @Override
    public String toString() {
        return username;
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

    public LinkedList<Utente> getMyUtentes() {
        return utentes;
    }

    public void addUtente(Utente utente) {
        utentes.add(utente);
    }

    public LinkedList<Utente> getUtentes() {
        return utentes;
    }

    public LinkedList<ProcedimentoAplicado> getProcedimentosAplicados() {
        return procedimentosAplicados;
    }

    public void setProcedimentosAplicados(LinkedList<ProcedimentoAplicado> procedimentosAplicados) {
        this.procedimentosAplicados = procedimentosAplicados;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

}
