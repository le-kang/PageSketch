package au.edu.uts.aip.pagesketch;

/**
 *
 * @author Le Kang
 */
public class Star {
    private String username;
    private String pageId;

    /**
     * 
     * @param username
     * @param pageId 
     */
    public Star(String username, String pageId) {
        this.username = username;
        this.pageId = pageId;
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
    public String getPageId() {
        return pageId;
    }

    /**
     * 
     * @param pageId 
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    
}
