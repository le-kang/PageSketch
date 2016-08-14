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
     * 
     * @return 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return 
     */
    public int getVersion() {
        return version;
    }

    /**
     * 
     * @param version 
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 
     * @return 
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code 
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     * @return 
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt 
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
