/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dtos.AdministradorDTO;
import dtos.CuidadorDTO;
import ejbs.AdministradorBean;
import ejbs.CuidadorBean;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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

    private UIComponent component;

    private static final Logger logger = Logger.getLogger("web.AdministratorManager");

    //////ADMINISTRADOR/////////
    public AdministratorManager() {
        newAdministrador = new AdministradorDTO();
        newCuidador = new CuidadorDTO();
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
    
    public void getUtentesFromCurrentCuidador(){
        
    }

    //////////////FIM DE CUIDADOR//////////////
    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
}
