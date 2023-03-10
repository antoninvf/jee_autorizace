package cz.antoninvf.services;

import cz.antoninvf.beans.LoginBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RegisterService {
    private String email;
    private String fullName;
    private String password;

    private LoginBean loginBean;

    public RegisterService() {
        loginBean = new LoginBean();
    }

    public void register() {
        if (!loginBean.isUserRegistered(email)) {
                loginBean.registerUser(fullName, email, password);
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
