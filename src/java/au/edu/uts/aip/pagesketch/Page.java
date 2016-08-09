package au.edu.uts.aip.pagesketch;

/**
 * A class that stores basic information of a web page created by user
 * 
 * @author Le Kang
 */
public class Page {
    private String id;
    private String name;
    private int currentVersion;
    private String createdBy;
    private boolean published;

    /**
     * Constructor to create new page
     * 
     * @param id the id of the page
     * @param name the name of the page
     * @param currentVersion the current version of the page
     * @param createdBy the user name who creates the page
     * @param published the flag shows whether the page is published for share
     */
    public Page(String id, String name, int currentVersion, String createdBy, boolean published) {
        this.id = id;
        this.name = name;
        this.currentVersion = currentVersion;
        this.createdBy = createdBy;
        this.published = published;
    }

    /**
     * Get the id of the page. 
     * 
     * @return the id of the page
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id of the page.
     * <p>
     * Must in a UUID format and unique.
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the name of the page.
     * 
     * @return the name of the page
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the page.
     * <p>
     * Must be unique if created by the same user
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the current version of the page
     * 
     * @return the current version of the page
     */
    public int getCurrentVersion() {
        return currentVersion;
    }

    /**
     * Set the current version of the page
     * <p>
     * The current version indicate the latest version of the page in version
     * table, starts from 1
     * 
     * @param currentVersion 
     */
    public void setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
    }

    /**
     * Get the user who created this page
     * <p>
     * The user must be registered in user table
     * 
     * @return 
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the user who created this page
     * 
     * @param createdBy 
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the flag whether the user published the page for share
     * 
     * @return 
     */
    public boolean getPublished() {
        return published;
    }

    /**
     * Set the flag whether the user would like to publish page for share
     * 
     * @param published 
     */
    public void setPublished(boolean published) {
        this.published = published;
    }
}
