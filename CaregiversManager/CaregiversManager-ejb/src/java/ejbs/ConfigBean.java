/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;


import exceptions.EntityDoesNotExistsException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @EJB
    private CuidadorBean cuidadorBean;
    
    @EJB
    private UtenteBean utenteBean;


    @PostConstruct
    public void populateBD() {

        administradorBean.create("baby", "João Serrano", "joaoreidaselvaedomundo123");
        administradorBean.create("baby2", "João Serrano o Rei da Selva", "joaorei");
        administradorBean.create("baby7", "Numero Perfeito", "PerfeitoSoueu");  
        administradorBean.create("ola", "asdas", "123");
        profissionalBean.create("babyboo", "João Rei", "joaoomaiordestemundoonossorei");
        cuidadorBean.create("kiko", "FranciscoGiraço", "1245");
        
        utenteBean.create(1, "VelhaXata2");
        
        try {
            utenteBean.giveUtenteToCuidador(1, "kiko");
        } catch (EntityDoesNotExistsException ex) {
            Logger.getLogger(ConfigBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        utenteBean.create(1,"kiko");
        utenteBean.create(2,"asd");

    }
}
