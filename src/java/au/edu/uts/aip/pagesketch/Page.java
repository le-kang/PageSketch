package au.edu.uts.aip.pagesketch;

import java.util.ArrayList;
import javax.validation.constraints.Size;

/**
 * A class that stores basic information of a web page created by user
 * 
 * @author Le Kang
 */
public class Page {
    private String id;
    @Size(min=1, message="Page name is mandatory")
    private String name;
    @Size(max=255, message="Page description cannot be more than 255 characters.")
    private String description;
    private int currentVersion;
    private String author;
    private boolean published = false;
    private ArrayList<PageVersion> versions = new ArrayList();
    private ArrayList<Star> stars = new ArrayList();

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
     * 
     * @return 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
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
    public String getAuthor() {
        return author;
    }

    /**
     * Set the user who created this page
     * 
     * @param author 
     */
    public void setAuthor(String author) {
        this.author = author;
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

    /**
     * 
     * @return 
     */
    public ArrayList<PageVersion> getVersions() {
        return versions;
    }

    /**
     * 
     * @param versions 
     */
    public void setVersions(ArrayList<PageVersion> versions) {
        this.versions = versions;
    }
    
    /**
     * 
     * @return 
     */
    public PageVersion getCurrentPageVersion() {
        return versions.get(0);
    }

    /**
     * 
     * @return 
     */
    public ArrayList<Star> getStars() {
        return stars;
    }

    /**
     * 
     * @param stars 
     */
    public void setStars(ArrayList<Star> stars) {
        this.stars = stars;
    }
    
    /**
     * 
     * @param version
     * @return 
     */
    public PageVersion getPageVersion(int version) {
        return versions.get(versions.size() - version);
    }
}
