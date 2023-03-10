package cz.antoninvf.services;

import cz.antoninvf.beans.LoginBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class LoginService {
    private String email;
    private String password;
    private boolean isLoggedIn = false;
    private LoginBean loginBean;

    public LoginService() {
        loginBean = new LoginBean();
    }

    public void before(ComponentSystemEvent event) {
        if (!FacesContext.getCurrentInstance().isPostback()){
            // If not logged in, redirect to login page
            if (!isLoggedIn) {
                FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");
            }
        }
    }

    public void login() {
        if (loginBean.isUserRegistered(email)) if (loginBean.isLoginValid(email, password)) isLoggedIn = true;
    }

    public void logout() {
        if (isLoggedIn) {
            isLoggedIn = false;
            email = "";
            password = "";
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
