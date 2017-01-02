/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
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
 * @author Marta
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getAllNecessidades",
            query = "SELECT m FROM Necessidade m")})
public class Necessidade implements Serializable {

    @Id
    private int id;
    @NotNull
    private String nome;
    private String descricao;

    @ManyToMany(mappedBy = "necessidades")
    private LinkedList<MaterialDeCapacitacao> materiais;

    @ManyToMany
    @JoinTable(name = "UTENTE_NECESSIDADE",
            joinColumns
            = @JoinColumn(name = "UTENTE_CODE", referencedColumnName = "ID"),
            inverseJoinColumns
            = @JoinColumn(name = "NECESSIDADE_CODE", referencedColumnName = "ID"))
    private LinkedList<Utente> utentes;

    public Necessidade() {

    }

    public Necessidade(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.materiais = new LinkedList<>();
        this.utentes = new LinkedList<>();

    }

    public LinkedList<Utente> getUtentes() {
        return utentes;
    }

    public void setUtentes(LinkedList<Utente> utentes) {
        this.utentes = utentes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescrição(String descricao) {
        this.descricao = descricao;
    }

    public LinkedList<MaterialDeCapacitacao> getMateriais() {
        return materiais;
    }

    public void setMateriais(LinkedList<MaterialDeCapacitacao> materiais) {
        this.materiais = materiais;
    }

    public void addMaterial(MaterialDeCapacitacao m) {
        this.materiais.add(m);
    }

    public void addUtente(Utente e) {
        this.utentes.add(e);
    }

}
