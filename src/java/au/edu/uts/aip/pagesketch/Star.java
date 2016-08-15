package au.edu.uts.aip.pagesketch;

/**
 * This class represents a star record that a user stars a page
 *
 * @author Le Kang
 */
public class Star {
    private String username;
    private String pageId;

    /**
     * 
     * @param username the username who starred the page
     * @param pageId the page ID which is starred
     */
    public Star(String username, String pageId) {
        this.username = username;
        this.pageId = pageId;
    }

    /**
     * Get the user who starred the page
     * 
     * @return the user who starred the page
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the user who starred the page
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * The the page ID which is starred
     * 
     * @return the page ID which is starred
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * Set the page ID which is starred
     * 
     * @param pageId 
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    
}
