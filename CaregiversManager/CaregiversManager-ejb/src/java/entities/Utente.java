/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joaos
 */

/*@NamedQueries({
@NamedQuery(name = "getAllMyUtentes",
    query = "SELECT s FROM Utente s WHERE s.cuidador.username = :username ORDER BY s.name")})*/
@Entity
public class Utente implements Serializable {

    @Id
    private int id;
    @NotNull
    private String name;

    //ligações
    @ManyToOne
    @JoinColumn(name = "CUIDADOR_CODE")
    private Cuidador cuidador;

    @ManyToMany(mappedBy = "utentes")
    private LinkedList<Necessidade> necessidades;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.REMOVE)
    LinkedList<ProcedimentoAplicado> procedimentosAplicados;

    public Utente() {
        this.necessidades = new LinkedList<>();
        this.procedimentosAplicados = new LinkedList<>();
        this.cuidador = null;
    }

    public Utente(int id, String name) {
        this.name = name;
        this.id = id;
        this.cuidador = null;
        this.necessidades = new LinkedList<>();
        this.procedimentosAplicados = new LinkedList<>();
    }

    public LinkedList<ProcedimentoAplicado> getProdecimentosAplicados() {
        return procedimentosAplicados;
    }

    public void setProdecimentosAplicados(LinkedList<ProcedimentoAplicado> prodecimentosAplicados) {
        this.procedimentosAplicados = prodecimentosAplicados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Necessidade> getNecessidades() {
        return necessidades;
    }

    public void setNecessidades(LinkedList<Necessidade> necessidades) {
        this.necessidades = necessidades;
    }

    public void addCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

}
