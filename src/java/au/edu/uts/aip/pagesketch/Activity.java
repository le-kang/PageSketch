package au.edu.uts.aip.pagesketch;

import java.util.Date;

/**
 * A class stores the user's recent activities such as create, update and star
 * a page
 *
 * @author Le Kang
 */
public class Activity {
    private String username;
    private String action;
    private String pageName;
    private String pageId;
    private Date createdAt;

    /**
     * Get the username of the activity
     * 
     * @return the username in this activity
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the activity
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the action of the activity
     * <p>
     * Possible values: Created, Updated, Starred
     * 
     * @return the action of the activity
     */
    public String getAction() {
        return action;
    }

    /**
     * Set the action of the activity
     * <p>
     * Possible values: Created, Updated, Starred
     * 
     * @param action 
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Get the page name of the activity
     * 
     * @return the page name of the activity
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * Set the page name of the activity
     * 
     * @param pageName 
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /**
     * Get the page ID of the activity
     * 
     * @return the page ID of the activity
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * Set the page ID of the activity
     * 
     * @param pageId 
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * Get the time when the activity happens
     * 
     * @return 
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the time when the activity happens
     * 
     * @param createdAt 
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
