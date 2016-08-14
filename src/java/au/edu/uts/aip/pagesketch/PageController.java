package au.edu.uts.aip.pagesketch;

import java.io.Serializable;
import java.util.ArrayList;
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
    
    public void loadPage() {
        if (null != id) {
            try {
                PageDAO pageDAO = new PageDAO();
                page = pageDAO.find(id);
                pageVersion = page.getCurrentPageVersion();
                if (version > page.getCurrentVersion() || version < 1) {
                    version = page.getCurrentVersion();
                }
                code = page.getPageVersion(version).getCode();
            } catch (NamingException ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setAuthor(String username) {
        page.setAuthor(username);
    }
    
    public String createOrUpdate() {
        return id == null ? create() : update();
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
        return "page?faces-redirect=true&includeViewParams=true";
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
        return "page?faces-redirect=true&includeViewParams=true";
    }
    
}
