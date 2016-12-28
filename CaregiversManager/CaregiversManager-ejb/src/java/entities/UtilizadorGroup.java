/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name="UTILIZADORES_GROUPS")
public class UtilizadorGroup implements Serializable {

    @Id
    @Enumerated(EnumType.STRING)
    private GROUP group_Name;

    @Id
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private Utilizador user;

    public UtilizadorGroup() {

    }

    public UtilizadorGroup(GROUP group, Utilizador u) {
        group_Name = group;
        user = u;
    }

    public GROUP getGroupName() {
        return group_Name;
    }

    public Utilizador getUser() {
        return user;
    }
    
    
}
