/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejbs.ProfissionalSaudeBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.inject.Named;

/**
 *
 * @author Francisco
 */
@ManagedBean
@Named(value = "administratorManager")
@SessionScoped
public class ProfissionalSaudeManager implements Serializable {

    @EJB
    private ProfissionalSaudeBean profissionalBean;
    
    
    private UIComponent component;

    private static final Logger logger = Logger.getLogger("web.ProfissionalSaudeManager");

    public ProfissionalSaudeManager(){
        
    }
    
    
    public UIComponent getComponent() {
        return component;
    }
    
    

    public void setComponent(UIComponent component) {
        this.component = component;
    }
}
