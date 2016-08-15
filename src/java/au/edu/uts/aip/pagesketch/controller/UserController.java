package au.edu.uts.aip.pagesketch.controller;

import au.edu.uts.aip.pagesketch.dataaccess.UserDAO;
import au.edu.uts.aip.pagesketch.model.Activity;
import au.edu.uts.aip.pagesketch.model.User;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;

/**
 * Backing bean for handling user login, logout, registration
 * and list the recent activities for this user
 *
 * @author Le Kang
 */
@Named
@SessionScoped
public class UserController implements Serializable {
    @Size(min=1, message="Please enter a username")
    private String username;
    @Size(min=1, message="Please enter a password")
    private String password;
    private User user = new User();
    private ArrayList<Activity> activities = new ArrayList();

    /**
     * Get the username for login
     * 
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username for login
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password for login
     * 
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password for login
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the user object
     * 
     * @return 
     */
    public User getUser() {
        return user;
    }

    /**
     * Get the user's recent activities
     * 
     * @return 
     */
    public ArrayList<Activity> getActivities() {
        return activities;
    }
    
    /**
     * Handle login with container-managed security
     * 
     * @return 
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(username, password);
            UserDAO userDAO = new UserDAO();
            user = userDAO.find(username);
        } catch (ServletException ex) {
            String message = ex.getMessage() + ": Invalid user name or password";
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return null;
        } catch (NamingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
        return "index?faces-redirect=true";
    }
    
    /**
     * Handle logout with container-managed security
     * 
     * @return 
     */
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            username = null;
            password = null;
            user = new User();
        } catch (ServletException ex) {
            context.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
        return "login?faces-redirect=true";
    }
    
    /**
     * Handle registration of a new user
     * 
     * @return 
     */
    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();
        String message;
        try {
            UserDAO userDAO = new UserDAO();
            if (null == userDAO.find(user.getUsername())) {
                userDAO.create(user);
                message = "User \"" + user.getUsername() + "\" registered successfully, please login";
                context.addMessage(null, new FacesMessage(message));
                context.getExternalContext().getFlash().setKeepMessages(true);
                user = new User();
            } else {
                message = "Username \"" + user.getUsername() + "\" already existed";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
                return null;
            }
        } catch (NamingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
        return "login?faces-redirect=true";
    }
    
    /**
     * Load current user's recently activities
     * 
     */
    public void loadActivities() {
        try {
            UserDAO userDAO = new UserDAO();
            activities = userDAO.findAllActivity(user.getUsername());
        } catch (NamingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
