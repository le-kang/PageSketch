package au.edu.uts.aip.pagesketch;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

/**
 *
 * @author Le Kang
 */
public class User {
    @Size(min=1, max=30, message="Username is mandatory and no more than 30 characters.")
    private String username;
    @Size(min=8, max=20, message="Password is mandatory and between 8 and 20 characters.")
    private String password;
    @Size(min=1, message="Full name is mandatory")
    private String fullName;
    @Pattern(regexp="^(.+)@(.+)$", message="Email is empty or invalid")
    private String email;
    private String bio;
    private Date createdAt;

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
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return 
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @param fullName 
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 
     * @return 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return 
     */
    public String getBio() {
        return bio;
    }

    /**
     * 
     * @param bio 
     */
    public void setBio(String bio) {
        this.bio = bio;
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
