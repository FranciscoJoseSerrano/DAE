/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dtos.AdministradorDTO;
import dtos.CuidadorDTO;
import dtos.MaterialDeCapacitacaoDTO;
import dtos.ProfissionalSaudeDTO;
import dtos.UtenteDTO;
import ejbs.AdministradorBean;
import ejbs.CuidadorBean;
import ejbs.MaterialDeCapacitacaoBean;
import ejbs.ProfissionalSaudeBean;
import ejbs.UtenteBean;
import entities.SuporteMaterialDeCapacitacao;
import entities.TipoMaterialDeCapacitacao;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author joaos
 */
@ManagedBean
@Named(value = "administratorManager")
@SessionScoped
public class AdministratorManager implements Serializable {

    @EJB
    private AdministradorBean administradorBean;
    private AdministradorDTO currentAdministrador;
    private AdministradorDTO newAdministrador;

    @EJB
    private CuidadorBean cuidadorBean;
    private CuidadorDTO newCuidador;
    private CuidadorDTO currentCuidador;

    @EJB
    private UtenteBean utenteBean;
    private UtenteDTO newUtente;
    private UtenteDTO currentUtente;

    @EJB
    private ProfissionalSaudeBean profissionalBean;
    private ProfissionalSaudeDTO newProfissional;
    private ProfissionalSaudeDTO currentProfissional;

    @EJB
    private MaterialDeCapacitacaoBean materialCapacitacaoBean;
    private MaterialDeCapacitacaoDTO newMaterialCapacitacao;
    private MaterialDeCapacitacaoDTO selectedMaterial;

    private SuporteMaterialDeCapacitacao[] suportes;
    private TipoMaterialDeCapacitacao[] tiposSuporte;

    private UIComponent component;

    private Client client;
    private WebTarget target;
    private final String baseUri = "http://localhost:8080/CaregiversManager-war/webapi";
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");

    //////ADMINISTRADOR/////////
    public AdministratorManager() {
        newAdministrador = new AdministradorDTO();
        newCuidador = new CuidadorDTO();
        newProfissional = new ProfissionalSaudeDTO();
        newUtente = new UtenteDTO();
        newMaterialCapacitacao = new MaterialDeCapacitacaoDTO();
        suportes = getAllMaterialCapacitacaoSuporte();
        tiposSuporte = getAllMaterialCapacitacaoTiposSuporte();
        client = ClientBuilder.newClient();
    }

    public UtenteDTO getNewUtente() {
        return newUtente;
    }

    public void setNewUtente(UtenteDTO newUtente) {
        this.newUtente = newUtente;
    }

    public UtenteDTO getCurrentUtente() {
        return currentUtente;
    }

    public void setCurrentUtente(UtenteDTO currentUtente) {
        this.currentUtente = currentUtente;
    }

    public ProfissionalSaudeDTO getNewProfissional() {
        return newProfissional;
    }

    public void setNewProfissional(ProfissionalSaudeDTO newProfissional) {
        this.newProfissional = newProfissional;
    }

    public ProfissionalSaudeDTO getCurrentProfissional() {
        return currentProfissional;
    }

    public void setCurrentProfissional(ProfissionalSaudeDTO currentProfissional) {
        this.currentProfissional = currentProfissional;
    }

    public String createAdministrador() {
        try {
            if (!existeAdmin(newAdministrador.getUsername())) {
                administradorBean.create(newAdministrador.getUsername(), newAdministrador.getNome(), newAdministrador.getPassword());
                newAdministrador.reset();
                return "first_page_admin?faces-redirect=true";
            }

            FacesContext.getCurrentInstance().addMessage("myAdmin:username", new FacesMessage("Erro: Já existe um Administrador com esse username"));

            return "administrador_criar?faces-redirect=true";

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
            return null;
        }

    }

    public List<AdministradorDTO> getAllAdministradores() {
        try {
            return administradorBean.getAllAdministradores();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public String updateAdministrador(AdministradorDTO administrador) {
        try {
            administradorBean.update(administrador);
            return "first_page_admin?faces-redirect=true";
        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Erro ocorrido ! Tente mais tarde", logger);
        }
        return "administrador_update";
    }

    public void removerAdministrador(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("administradorUsername");
            String username = param.getValue().toString();
            administradorBean.remove(username);

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Ocorreu um erro ! Tente mais tarde", logger);
        }
    }

    public AdministradorDTO procurarAdministrador(String username) {
        try {

            this.currentAdministrador = administradorBean.getAdministrador(username);
            if (currentAdministrador != null) {
                return currentAdministrador;

            } else {

                return null;
            }

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Erro ocorrido ! Tente mais tarde", logger);
            return null;
        }
    }

    public String verificarAdmin(String username) {

        if (existeAdmin(username)) {
            return "administrador_detalhes?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage("myForm:input1", new FacesMessage("Erro: Não existe nenhum Administrador com esse Username"));
        return "first_page_admin";

    }

    public boolean existeAdmin(String username) {
        AdministradorDTO administradorAux = procurarAdministrador(username);
        return administradorAux != null;
    }

    public AdministradorDTO getCurrentAdministrador() {
        return currentAdministrador;
    }

    public void setCurrentAdministrador(AdministradorDTO currentAdministrador) {
        this.currentAdministrador = currentAdministrador;
    }

    public AdministradorDTO getNewAdministrador() {
        return newAdministrador;
    }

    public void setNewAdministrador(AdministradorDTO newAdministrador) {
        this.newAdministrador = newAdministrador;
    }

    ////////////////FIM DE ADMINISTRADOR//////////////
    //////CUIDADOR///////
    public List<CuidadorDTO> getAllCuidadores() {
        try {
            return cuidadorBean.getCuidadores();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public String createCuidador() {
        try {
            if (!cuidadorBean.existsCuidador(newCuidador.getUsername())) {
                cuidadorBean.create(newCuidador.getUsername(), newCuidador.getNome(), newCuidador.getPassword());
                newCuidador.reset();
                return "cuidador_todos?faces-redirect=true";
            }

            FacesContext.getCurrentInstance().addMessage("myCuidador:username", new FacesMessage("Erro: Já existe um Cuidador com esse username"));

            return "cuidador_criar?faces-redirect=true";

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
            return null;
        }
    }

    public void setNewCuidador(CuidadorDTO newCuidador) {
        this.newCuidador = newCuidador;
    }

    public CuidadorDTO getNewCuidador() {
        return newCuidador;
    }

    public void removerCuidador(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("cuidadorUsername");
            String username = param.getValue().toString();
            cuidadorBean.remove(username);

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Ocorreu um erro ! Tente mais tarde", logger);
        }
    }

    public void setCurrentCuidador(CuidadorDTO currentCuidador) {
        this.currentCuidador = currentCuidador;
    }

    public CuidadorDTO getCurrentCuidador() {
        return currentCuidador;
    }

    public String updateCuidador(CuidadorDTO c) {
        try {
            cuidadorBean.update(c);
            return "cuidador_detalhes?faces-redirect=true";
        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Erro ocorrido ! Tente mais tarde", logger);
        }
        return "administrador_update";
    }

    public String verificarCuidador(String username) {

        if (existeCuidador(username)) {
            return "cuidador_detalhes?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage("procurarCuidadorForm:inputProcurarCuidador", new FacesMessage("Erro: Não existe nenhum Cuidador com esse Username"));
        return "cuidador_todos";

    }

    public boolean existeCuidador(String username) {
        CuidadorDTO cuidadorAux = procurarCuidador(username);
        return cuidadorAux != null;
    }

    public CuidadorDTO procurarCuidador(String username) {
        try {

            this.currentCuidador = cuidadorBean.getCuidador(username);
            if (this.currentCuidador != null) {
                return currentCuidador;

            } else {

                return null;
            }

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Erro ocorrido ! Tente mais tarde", logger);
            return null;
        }
    }

    public List<UtenteDTO> getUtentesFromCurrentCuidador() {
        try {
            return utenteBean.getMyUtentes(currentCuidador.getUsername());
        } catch (EntityDoesNotExistsException ex) {
            Logger.getLogger(AdministratorManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //////////////FIM DE CUIDADOR//////////////
    //////////////PROFISSIONAL SAUDE////////////////
    public String associarUtente(int id, String username) throws EntityDoesNotExistsException {
        if (existeCuidador(username)) {
            if (existsUtente(id)) {
                utenteBean.giveUtenteToCuidador(id, username);
            }
            FacesContext.getCurrentInstance().addMessage("utenteAssociarForm:input2", new FacesMessage("Erro: Não existe nenhum Cuidador com esse Username"));
        }
        return "cuidador_todos?faces-redirect=true";
    }

    public String createProfissional() {
        try {
            if (!existsProfissional(newProfissional.getUsername())) {
                profissionalBean.create(newProfissional.getUsername(), newProfissional.getNome(), newProfissional.getPassword());
                newProfissional.reset();
                return "first_page_admin?faces-redirect=true";
            }

            return "profissional_criar?faces-redirect=true";

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
            return null;
        }
    }

    public boolean existsProfissional(String username) {
        ProfissionalSaudeDTO profissionalAux = procurarProfissional(username);
        return profissionalAux != null;
    }

    public ProfissionalSaudeDTO procurarProfissional(String username) {
        try {

            this.currentProfissional = profissionalBean.getProfissionalSaude(username);
            if (this.currentProfissional != null) {
                return currentProfissional;

            } else {

                return null;
            }

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Erro ocorrido ! Tente mais tarde", logger);
            return null;
        }
    }

    public void removerProfissional(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("profissionalUsername");
            String username = param.getValue().toString();
            profissionalBean.remove(username);

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Ocorreu um erro ! Tente mais tarde", logger);
        }
    }

    public String updateProfissional(ProfissionalSaudeDTO profissional) {
        try {
            profissionalBean.update(profissional);
            return "first_page_admin?faces-redirect=true";
        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Erro ocorrido ! Tente mais tarde", logger);
        }
        return "profissional_update";
    }

    public List<ProfissionalSaudeDTO> allProfissionais() {
        try {
            return profissionalBean.getAllProfissionais();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public String verificarProfissionalSaude(String username) {

        if (existsProfissional(username)) {
            return "profissional_detalhes?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage("myForm2:input2", new FacesMessage("Erro: Não existe nenhum Profissional com esse Username"));
        return "first_page_admin";

    }

    //////////////FIM PROFISSIONAL SUADE//////////
    //////////////UTENTE///////////////////////
    public List<UtenteDTO> allUtentesSemCuidador() {
        try {
            return utenteBean.utentesSemCuidador();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public String createUtente() {
        try {
            if (!existsUtente(newUtente.getId())) {
                utenteBean.create(newUtente.getId(), newUtente.getName());
                newUtente.reset();
                return "cuidador_todos?faces-redirect=true";
            }

            return "profissional_criar_utente?faces-redirect=true";

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
            return null;
        }
    }

    public boolean existsUtente(int id) {
        UtenteDTO utenteAux = procurarUtente(id);
        return utenteAux != null;
    }

    public UtenteDTO procurarUtente(int id) {
        try {

            this.currentUtente = utenteBean.getUtente(id);
            if (this.currentUtente != null) {
                return currentUtente;

            } else {

                return null;
            }

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Erro ocorrido ! Tente mais tarde", logger);
            return null;
        }
    }

    //////////////FIM DE UTENTE////////////////
    
    //////MATERIAIS///////
    public List<MaterialDeCapacitacaoDTO> getAllMateriais() {
        try {
            return materialCapacitacaoBean.getMateriaisDeCapacitacao();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
    
        public MaterialDeCapacitacaoDTO getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(MaterialDeCapacitacaoDTO selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }
    


    public String createMaterialDeCapacitacao() {
        try {
            //if (!materialCapacitacaoBean.existsMaterial(newMaterialCapacitacao.getId())) {
                materialCapacitacaoBean.create(newMaterialCapacitacao.getDescricao(),newMaterialCapacitacao.getSuporte(),newMaterialCapacitacao.getTipoSuporte(),newMaterialCapacitacao.getLink());
                newMaterialCapacitacao.reset();
                return "material_capacitacao_todos?faces-redirect=true";
           // }

           // FacesContext.getCurrentInstance().addMessage("myAdmin:username", new FacesMessage("Erro: Já existe um Material com esse id"));

           // return "material_capacitacao_criar?faces-redirect=true";

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
            return null;
        }
    }

    public MaterialDeCapacitacaoDTO getNewMaterialCapacitacao() {
        return newMaterialCapacitacao;
    }

       public SuporteMaterialDeCapacitacao[] getAllMaterialCapacitacaoSuporte() {
        try {
            return SuporteMaterialDeCapacitacao.values();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
       
         public TipoMaterialDeCapacitacao[] getAllMaterialCapacitacaoTiposSuporte() {
         try {
            return TipoMaterialDeCapacitacao.values();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
       
    public void removerMaterialCapacitacao(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("material_id");
            Long id = Long.parseLong(param.getValue().toString());
            materialCapacitacaoBean.remove(id);

        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Ocorreu um erro ! Tente mais tarde", logger);
        }
    }


  

    public String updateMaterialDeCapacitacao(MaterialDeCapacitacaoDTO m) {
        try {
            materialCapacitacaoBean.update(m);
            return "material_capacitacao_todos?faces-redirect=true";
        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Erro ocorrido ! Tente mais tarde", logger);
        }
        return "material_update";
    }

    public String verificarMateriaisDeCapacitacao(String descricao) {

        if (existeMaterialCapacitacao(descricao)) {
            return "material_capacitacao_detalhes?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage("procurarMaterialCapacitacao:inputProcurarMaterial", new FacesMessage("Erro: Não existe nenhum Material com essa descricao"));
        return "material_capacitacao_todos";
        

    }

    public boolean existeMaterialCapacitacao(String descricao) {
        if(procurarMaterialCapacitacaoByDescription(descricao).size()>0){
            return true;
        }
     return false;
    }
 

    public List<MaterialDeCapacitacaoDTO> procurarMaterialCapacitacaoByDescription(String descricao) {
        try {
               List<MaterialDeCapacitacaoDTO> list = materialCapacitacaoBean.getMaterialCapacitacaoByDescricao(descricao);
               return list;              
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Erro ocorrido ! Tente mais tarde", logger);
            return null;
        }
    }
    
    ///////////////FIM MATERIAL//////////////////////
    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

  
}
