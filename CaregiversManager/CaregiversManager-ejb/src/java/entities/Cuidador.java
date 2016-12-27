package entities;

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @NamedQuery(name = "getAllCuidadores",
            query = "SELECT s FROM Cuidador s")})
public class Cuidador extends Utilizador implements Serializable {

    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.REMOVE)
    private LinkedList<Utente> utentes;

    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.REMOVE)
    LinkedList<ProcedimentoAplicado> procedimentosAplicados;

    public Cuidador() {
        utentes = new LinkedList<>();
        procedimentosAplicados = new LinkedList<>();
    }

    public Cuidador(String username, String nome, String password) {
        super(username,password,nome);
        utentes = new LinkedList<>();
        procedimentosAplicados = new LinkedList<>();
    }

    @Override
    public String toString() {
        return username;
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

}
