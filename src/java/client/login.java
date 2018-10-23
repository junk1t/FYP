/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author DEREK
 */
@ManagedBean(name = "login")
@SessionScoped
public class login implements Serializable {

    String username;
    String password;

    public login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public login() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String user = params.get("username");
        String pass = params.get("password");
        
        if (user.equals("admin") && pass.equals("admin")) {
            return "/menu.xhtml?faces-redirect=true";
            
        } else {
            fc.addMessage("loginForm", new FacesMessage("Username or password is incorrect"));
            return null;
        }
    }
}
