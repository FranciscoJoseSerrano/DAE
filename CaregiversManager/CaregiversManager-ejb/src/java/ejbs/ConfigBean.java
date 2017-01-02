/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.SuporteMaterialDeCapacitacao;
import entities.TipoMaterialDeCapacitacao;
import exceptions.EntityDoesNotExistsException;
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
            necessidadeBean.create(2, "Filho de um boi vai bater a bota", "ja foste");
            materialBean.create("Baby i like your style", SuporteMaterialDeCapacitacao.TEXTO, TipoMaterialDeCapacitacao.TUTORIAL, null);
            materialBean.create("baby", SuporteMaterialDeCapacitacao.TEXTO, TipoMaterialDeCapacitacao.TUTORIAL, null);
            materialBean.create("sdd", SuporteMaterialDeCapacitacao.TEXTO, TipoMaterialDeCapacitacao.TUTORIAL, null);
            materialBean.create("Teste", SuporteMaterialDeCapacitacao.TEXTO, TipoMaterialDeCapacitacao.PROCEDIMENTO, null);
            utenteBean.giveUtenteToCuidador(2, "kiko");
            utenteBean.giveUtenteToCuidador(1, "kiko");
            
            
            Integer x = 1;
            Long b = x.longValue();
            
            necessidadeBean.giveMateriaisNecessidades(1, b);
            //cuidadorBean.giveCuidadorToMateriais("kiko", b);
            x=3;
            b=x.longValue();
            necessidadeBean.giveMateriaisNecessidades(1, b);
            x=2;
            b= x.longValue();
            necessidadeBean.giveMateriaisNecessidades(2, b);
            x=4;
            b= x.longValue();
            necessidadeBean.giveMateriaisNecessidades(1, b);
            
            utenteBean.giveUtenteToNecessidade(1, 2);
            utenteBean.giveUtenteToNecessidade(1, 1);
            
            x=2;
            b= x.longValue();
            cuidadorBean.giveCuidadorToMateriais("kiko", b);
            
            x=4;
            b= x.longValue();
            cuidadorBean.giveCuidadorToMateriais("kiko", b);
            
        } catch (EntityDoesNotExistsException  e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
