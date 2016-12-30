/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.SuporteMaterialDeCapacitacao;
import entities.TipoMaterialDeCapacitacao;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
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

    @EJB
    private NecessidadeBean necessidadeBean;

    @EJB
    private MaterialDeCapacitacaoBean materialBean;

    @PostConstruct
    public void populateBD() {
        try {
            administradorBean.create("baby", "João Serrano", "joaoreidaselvaedomundo123");
            administradorBean.create("baby2", "João Serrano o Rei da Selva", "joaorei");
            administradorBean.create("baby7", "Numero Perfeito", "PerfeitoSoueu");
            administradorBean.create("ola", "asdas", "123");
            profissionalBean.create("babyboo", "João Rei", "joaoomaiordestemundoonossorei");
            cuidadorBean.create("kiko", "FranciscoGiraço", "1245");

            utenteBean.create(1, "VelhaXata2");

            utenteBean.create(3, "kiko");
            utenteBean.create(2, "asd");
            necessidadeBean.create(1, "esta doente o filho da puta", "é para o cu, esse cabrão");
            materialBean.create("Baby i like your style", SuporteMaterialDeCapacitacao.TEXTO, TipoMaterialDeCapacitacao.TUTORIAL, null);
            materialBean.create("baby", SuporteMaterialDeCapacitacao.TEXTO, TipoMaterialDeCapacitacao.TUTORIAL, null);

            Integer x = 1;
            Long b = x.longValue();
            necessidadeBean.giveMateriaisNecessidades(1, b);
        } catch (EntityDoesNotExistsException  e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
