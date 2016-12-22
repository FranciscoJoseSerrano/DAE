/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dtos.AdministradorDTO;
import ejbs.AdministradorBean;
import exceptions.EntityAlreadyExistsException;
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

   
    
    
    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    private static final Logger logger = Logger.getLogger("web.AdministratorManager");

    //////ADMINISTRADOR/////////
    public AdministratorManager() {
    }
    
    public String createAdministrador(){
        try{
            administradorBean.create(newAdministrador.getUsername(), newAdministrador.getName(), newAdministrador.getPassword());
            newAdministrador.reset();
            return "first_page_admin?faces-redirect=true";
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
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
    
    public String verificarAdmin(String username){

         AdministradorDTO administradorAux = procurarAdministrador(username);
         if (administradorAux != null){
             return "administrador_detalhes?faces-redirect=true";
         }
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensagem de erro", "Não foi encontrado utilizador!"));
         return "first_page_admin";
         
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
}
