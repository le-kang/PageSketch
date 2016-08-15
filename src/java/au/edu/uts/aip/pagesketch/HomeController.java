package au.edu.uts.aip.pagesketch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;

/**
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

    public void loadPages(String username) {
        user = username;
        try {
            PageDAO pageDAO = new PageDAO();
            pages = pageDAO.findAllPages();
        } catch (NamingException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Page> getOtherPages() {
        for (Page page: pages) {
            if (!page.getAuthor().equals(user) && page.getPublished()) {
                otherPages.add(page);
            }
        }
        return otherPages;
    }
    
    public ArrayList<Page> getMyPages() {
        for (Page page: pages) {
            if (page.getAuthor().equals(user)) {
                myPages.add(page);
            }
        }
        return myPages;
    }
}
