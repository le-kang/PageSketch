package au.edu.uts.aip.pagesketch.controller;

import au.edu.uts.aip.pagesketch.dataaccess.PageDAO;
import au.edu.uts.aip.pagesketch.model.Page;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 * Backing bean for getting all the pages belong to current user and 
 * pages from other users for share
 *
 * @author Le Kang
 */
@Named
@RequestScoped
public class HomeController implements Serializable {
    private ArrayList<Page> pages = new ArrayList();
    private ArrayList<Page> otherPages = new ArrayList();
    private ArrayList<Page> myPages = new ArrayList();
    private String user;

    /**
     * Load all pages and set current user in controller context
     * 
     * @param username 
     */
    public void loadPages(String username) {
        user = username;
        try {
            PageDAO pageDAO = new PageDAO();
            pages = pageDAO.findAllPages();
        } catch (NamingException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Find pages from other users for share
     * 
     * @return pages from other users for share
     */
    public ArrayList<Page> getOtherPages() {
        for (Page page: pages) {
            if (!page.getAuthor().equals(user) && page.getPublished()) {
                otherPages.add(page);
            }
        }
        return otherPages;
    }
    
    /**
     * Find pages belong to the current user
     * 
     * @return pages belong to the current user
     */
    public ArrayList<Page> getMyPages() {
        for (Page page: pages) {
            if (page.getAuthor().equals(user)) {
                myPages.add(page);
            }
        }
        return myPages;
    }
}
