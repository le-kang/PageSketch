package au.edu.uts.aip.pagesketch;

import java.util.Date;

/**
 * This class represents a single version of a web page.
 * <p>
 * User can keep track of a history of a specific web page.
 * 
 * @author Le Kang
 */
public class PageVersion {
    private String id;
    private int version;
    private String code;
    private Date createdAt;
    
    /**
     * Get the page ID
     * 
     * @return the page ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set the page ID
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the version number of this version
     * 
     * @return version number
     */
    public int getVersion() {
        return version;
    }

    /**
     * Set the version number 
     * 
     * @param version 
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Get the code in this version
     * 
     * @return 
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the code for this version
     * 
     * @param code 
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get when this version is created
     * 
     * @return the time when this version is created
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set when this version is created
     * 
     * @param createdAt 
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
