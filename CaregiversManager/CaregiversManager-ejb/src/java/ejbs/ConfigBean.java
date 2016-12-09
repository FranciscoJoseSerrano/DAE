/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author joaos
 */
@Singleton
@Startup
public class ConfigBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private AdministradorBean administradorBean;

    @PostConstruct
    public void populateBD() {

        administradorBean.create(0, "João Serrano", "joaoreidaselvaedomundo123");
        administradorBean.create(1, "João Serrano o Rei da Selva", "joaorei");
        administradorBean.create(7, "Numero Perfeito", "PerfeitoSoueu");

    }
}
