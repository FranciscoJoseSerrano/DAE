/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dtos.AdministradorDTO;
import ejbs.AdministradorBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;


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
    private AdministradorDTO administrador;
    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
    
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");

    public AdministratorManager() {
    }
    
    public List<AdministradorDTO> getAllAdministradores() {
        try {
            return administradorBean.getAllAdministradores();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
}