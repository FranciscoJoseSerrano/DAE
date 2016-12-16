/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;


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
    
    @EJB
    private ProfissionalSaudeBean profissionalBean;

    @PostConstruct
    public void populateBD() {

        administradorBean.create("baby", "João Serrano", "joaoreidaselvaedomundo123");
        administradorBean.create("baby2", "João Serrano o Rei da Selva", "joaorei");
        administradorBean.create("baby7", "Numero Perfeito", "PerfeitoSoueu");
        
        profissionalBean.create("babyboo", "João Rei", "joaoomaiordestemundoonossorei");

    }
}
