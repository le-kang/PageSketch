package au.edu.uts.aip.pagesketch;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
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

    /**
     * 
     * @return 
     */
    public User getUser() {
        return user;
    }

    /**
     * 
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
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
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid user name or password", ""));
            return null;
        } catch (NamingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
        return "index?faces-redirect=true";
    }
    
    /**
     * 
     * @return 
     */
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            context.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
        return "login?faces-redirect=true";
    }
    
    /**
     * 
     * @return 
     */
    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            UserDAO userDAO = new UserDAO();
            if (null == userDAO.find(user.getUsername())) {
                user.setCreatedAt(new Date());
                userDAO.create(user);
                context.addMessage(null, new FacesMessage("User \"" + user.getUsername() + "\" registered successfully, please login"));
                context.getExternalContext().getFlash().setKeepMessages(true);
                user = new User();
            } else {
                context.addMessage(null, new FacesMessage("Username \"" + user.getUsername() + "\" already existed"));
                return null;
            }
        } catch (NamingException | NoSuchAlgorithmException | SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
        return "login?faces-redirect=true";
    }
}
