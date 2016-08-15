package au.edu.uts.aip.pagesketch;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 *
 * @author Le Kang
 */
@Named
@RequestScoped
public class PageController implements Serializable {
    private String id;
    private int version;
    private Page page = new Page();
    private PageVersion pageVersion = new PageVersion();
    private String code;
    private String user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    public Page getPage() {
        return page;
    }
    
    public PageVersion getPageVersion() {
        return pageVersion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public String loadPage(String username, String mode) {
        user = username;
        FacesContext context = FacesContext.getCurrentInstance();
        String message;
        if (null != id) {
            try {
                PageDAO pageDAO = new PageDAO();
                page = pageDAO.find(id);
                if (null == page) {
                    message = "Page(" + id + ")is not found";
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
                    context.getExternalContext().getFlash().setKeepMessages(true);
                    return "index?faces-redirect=true";
                } 
                if (mode.equals("edit") && !page.getAuthor().equals(username)) {
                    message = "You do not have the access to edit page(" + id + ")";
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
                    context.getExternalContext().getFlash().setKeepMessages(true);
                    return "index?faces-redirect=true";
                }
                if (mode.equals("view") && !page.getAuthor().equals(username) && !page.getPublished()) {
                    message = "You do not have the access to view page(" + id + ") as the author doesn't allow for share";
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
                    context.getExternalContext().getFlash().setKeepMessages(true);
                    return "index?faces-redirect=true";
                }
                pageVersion = page.getCurrentPageVersion();
                if (version > page.getCurrentVersion() || version < 1) {
                    version = page.getCurrentVersion();
                }
                code = page.getPageVersion(version).getCode();
            } catch (NamingException ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
                return "index?faces-redirect=true";
            }
        } else {
            if (mode.equals("view")) {
                return "index?faces-redirect=true";
            }
            page.setAuthor(username);
        }
        return null;
    }
    
    public String getStarsInfo() {
        int numOfStars = page.getStars().size();
        switch (numOfStars) {
            case 0:
                return "";
            case 1:
                return "<i class=\"fa fa-star\"></i>&nbsp;"
                        + "<i>" + page.getStars().get(0).getUsername() + "</i> ";
            case 2:
                return "<i class=\"fa fa-star\"></i>&nbsp;"
                        + "<i>" + page.getStars().get(0).getUsername() + "</i> and "
                        + "<i>" + page.getStars().get(1).getUsername() + "</i> ";
            default:
                return "<i class=\"fa fa-star\"></i>&nbsp;"
                        + "<i>" + page.getStars().get(0).getUsername() + "</i>, "
                        + "<i>" + page.getStars().get(1).getUsername() + "</i> "
                        + "and " + Integer.toString(numOfStars - 2) + "more...";
        }
    }
    
    public String getStarButtonLabel() {
        return isUserStarred() ? "Unstar": "Star";
    }
    
    public String convertCodeToBase64DataURL() {
        return "data:text/html;charset=utf-8;base64," + 
                Base64.getEncoder().encodeToString(code.getBytes(StandardCharsets.UTF_8));
    }
    
    public String createOrUpdate() {
        return id == null ? create() : update();
    }
    
    public String starOrUnstar() {
        try {
            PageDAO pageDAO = new PageDAO();
            page = pageDAO.find(id);
            Star star = new Star(user, id);
            if (isUserStarred()) {
                pageDAO.removeStar(star);
            } else {
                pageDAO.createStar(star);
            }
        } catch (NamingException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return "viewpage?faces-redirect=true&includeViewParams=true"; 
    }
    
    private String create() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            PageDAO pageDAO = new PageDAO();
            // Check whether the name is used on this account
            if (!pageDAO.found(page.getName(), page.getAuthor())) {
                id = UUID.randomUUID().toString();
                version = 1;
                page.setId(id);
                page.setCurrentVersion(1);
                pageVersion.setCode(code);
                pageVersion.setVersion(version);
                pageDAO.create(page);
                pageDAO.createNewVersion(id, pageVersion);
            } else {
                String message = "Page name already existed on this account";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
                return null;
            }
        } catch (NamingException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
        return "editpage?faces-redirect=true&includeViewParams=true";
    }
    
    /**
     *
     * @return
     */
    private String update() {
        FacesContext context = FacesContext.getCurrentInstance();
        id = page.getId();
        try {
            PageDAO pageDAO = new PageDAO();
            // Check whether the name is used on this account.
            // If name is not changed, exclude the current one.
            if (!pageDAO.found(id, page.getName(), page.getAuthor())) {
                // Only create new version if the code is updated from current version
                if (!code.equals(pageVersion.getCode())) {
                    version = page.getCurrentVersion() + 1;
                    page.setCurrentVersion(version);
                    pageVersion.setVersion(version);
                    pageVersion.setCode(code);
                    pageDAO.createNewVersion(id, pageVersion);
                }
                pageDAO.update(page);
            } else {
                String message = "Page name already existed on this account";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
                return null;
            }
        } catch (NamingException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
        return "editpage?faces-redirect=true&includeViewParams=true";
    }
    
    private boolean isUserStarred() {
        for (Star star: page.getStars()) {
            if (star.getUsername().equals(user)) {
                return true;
            }
        }
        return false;
    }
}
